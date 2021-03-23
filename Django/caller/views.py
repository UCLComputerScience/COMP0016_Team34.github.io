from django.http.response import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import  render
from django.http import HttpResponseRedirect
from django.contrib.auth import authenticate, login, logout

try:
    from .secret import setup
    setup() #were on local machine
except:
    pass #Were on azure

from django_twilio.decorators import twilio_view
from twilio.twiml.messaging_response import MessagingResponse


from .Caller import Caller
from .Url_to_send import Url_to_send
from .Form import Dataform, DescForm

import uuid
import json
from googletrans import Translator
translator = Translator()

callers = {}
urls_to_send = {}


def get_home(request):
    if request.method == "POST":
        form = Dataform(request.POST)
        if form.is_valid():
            data = form.cleaned_data
            caller_id = uuid.uuid4().int  # generates a unique id
            response = HttpResponseRedirect("/queue/")
            response.set_cookie("id", str(caller_id))

            fname = data["firstname"]
            sname = data["surname"]
            dob = data["dob"]

            c = Caller(fname+" "+sname,
                       dob, caller_id)
            callers[str(caller_id)] = c
            return response
    form = Dataform()
    return render(request, "caller/index.html", {"form": form})

# needs login
def get_JSON(request):
    if request.user.is_authenticated:
        to_remove = []
        json = "{'callers':["
        for caller in callers:
            caller_json = callers[caller].to_JSON()
            if(len(caller_json) != 1):
                json += str(caller_json)
                json += ","
                callers[caller].clear_changes()
                if "'active': False" in json:
                    to_remove.append(caller)
        json = json[:len(json)-1]
        json += "]}"
        for caller in to_remove:
            del callers[caller]
        return JsonResponse(json, safe=False)
    else:
        return HttpResponse(status=403)

# needs login


def get_static_JSON(request):
    if request.user.is_authenticated:
        c = Caller("Bob Smith", "11/1/2001",uuid.uuid4())
        c.add_description("My leg is broken")
        return JsonResponse(c.to_JSON(), safe=False)
    else:
        return HttpResponse(status=403)

# needs login


def clear_data(request):
    if request.user.is_authenticated:
        callers.clear()
        return HttpResponseRedirect("/processInfo/")
    else:
         return HttpResponse(status=403)

# needs login


def get_All_JSON(request):
    if request.user.is_authenticated:
        json = "{'callers':["
        for caller in callers:
            json += str(callers[caller].get_All())
            json += ","
            callers[caller].clear_changes()
        
        if json[len(json)-1] == ',':
            json = json[:len(json)-1]
        json += "]}"
        return JsonResponse(json, safe=False)
    else:
         return HttpResponse(status=403)



def get_queue(request):
    desc = False
    try:
        caller_id = request.COOKIES.get('id')
    except:
        return HttpResponseRedirect("/cookieWarning/")
    if request.method == "POST":
        form = DescForm(request.POST)
        if form.is_valid():
            try:
                # Cookie put in browser by google translate
                lang = request.COOKIES.get('googtrans')
                if lang == None:
                    lang = '/en/en'
            except:
                lang = '/en/en'
            
            try:
                callers[caller_id].add_language(lang)
            except:
                return HttpResponseRedirect("/cookieWarning/")
            data = form.cleaned_data
            desc = translator.translate(data["desc"]).text
            callers[caller_id].add_description(desc)

    # for caller in callers:
    #     print(callers[caller].id, callers[caller].description)
    form = DescForm()
    return render(request, "caller/queue.html", {"desc_form": form, "id": caller_id,"description":desc})


def update_caller_time(request):
    try:
        caller_id = request.COOKIES.get('id')
    except:
        return HttpResponseRedirect("/cookieWarning/")
    if caller_id in urls_to_send:
        return HttpResponse(json.dumps({"url": "/links/"}), content_type='application/json')
    if request.method == "POST":
        caller_id = str(request.POST.get("id"))
        try:
            callers[caller_id].update_time()
        except:
            return HttpResponse(json.dumps({"url": "/cookieWarning/"}), content_type='application/json')

    return HttpResponse(status=204)


# needs login
def get_changes(request):
    if request.user.is_authenticated:
        to_remove = []
        json = "{'callers':["
        for caller in callers:
            changes = callers[caller].to_JSON()
            if(len(changes) != 1):
                json += str(changes)
                json += ","
            callers[caller].clear_changes()
            if not callers[caller].is_active():
                to_remove.append(caller)

        for caller in to_remove:
            del callers[caller]
    
        if json[len(json)-1] == ',':
            json = json[:len(json)-1]
        json += "]}"
        return JsonResponse(json, safe=False)
    else:
         return HttpResponse(status=403)


@csrf_exempt
# needs login
def add_url(request):
    if request.user.is_authenticated:
        if request.method == "POST":
            url = request.POST['url']
            caller_id = str(request.POST['id'])
            desc = request.POST['description']
            text = request.POST['text']
            urls_to_send[caller_id] = Url_to_send(url, desc,text)
        return HttpResponse(status=204)
    else:
         return HttpResponse(status=403)


@csrf_exempt
def login_view(request):
    if request.method == "POST":
        username = request.POST["username"]
        password = request.POST["password"]
        if login_user(username,password,request):
            return HttpResponse("True")
        else:
            return HttpResponse("False")

def login_user(username, password, request):
    user = authenticate(request, username=username, password=password)
    if user is not None:
        login(request, user)
        return True
    else:
        return False


def is_logged_in(request):
    if request.user.is_authenticated:
        return HttpResponse("True")
    else:
         return HttpResponse("False")


def logout_view(request):
    logout(request)
    return HttpResponse("True")

@csrf_exempt
def get_queue_position(request):
    try:
        caller_id = request.POST['id']
    except:
        return HttpResponse("0")
    if request.method == "POST":
        pos = get_pos(caller_id)
        return HttpResponse(str(pos))
    

def get_pos(caller_id):
    if caller_id != 'None' and caller_id in callers:
        caller_time = callers[caller_id].get_start()
        position = 1
        for caller_id in callers:
            if callers[caller_id].get_start() < caller_time:
                position += 1
        return position 
    else:
        return 0;


def get_warning_screen(request):
    return render(request,"caller/warning.html")

def get_news_screen(request):
    return render(request,"caller/news.html")

def get_cookie_warning_screen(request):
    return render(request,"caller/CookieWarning.html")

def show_links(request):

    try:
        caller_id = request.COOKIES.get('id')
    except:
        return HttpResponseRedirect("/cookieWarning/")
    if str(caller_id) in urls_to_send:
        url = urls_to_send[caller_id]
        del urls_to_send[caller_id]
        del callers[caller_id]
        return render(request,"caller/links.html",{"sent_link":url.get_url(),"description":url.get_description(),"text":url.get_text()})
    
    del urls_to_send[caller_id]
    del callers[caller_id]
    return HttpResponseRedirect("/")

@twilio_view

def sms(request):
    r = MessagingResponse()
    URL = "https://team34-comp0016-2020.azurewebsites.net/" #Change this value for a different server
    r.message('Thank you for messaging the Q-Vu System. Please click this link: ' + URL + ' to join the queue')
    return str(r)
