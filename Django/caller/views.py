from json.encoder import JSONEncoder
from django.http.response import HttpResponse, JsonResponse
from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import get_object_or_404, redirect, render
from django.http import HttpResponseRedirect
from django.urls import reverse
from django.views import generic
from django.utils import timezone

# from .models import CallerModel
# import datetime

from .Caller import Caller
from .Form import Dataform, DescForm

import uuid
import json
from googletrans import Translator
translator = Translator()

callers = {}
url_to_send = {}


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
    else:
        form = Dataform()
    return render(request, "caller/index.html", {"form": form})


def get_JSON(request):
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


def get_static_JSON(request):
    c = Caller("Bob Smith", "11/1/2001")
    c.add_description("My leg is broken")
    return JsonResponse(c.to_JSON(), safe=False)


def clear_data(request):
    callers.clear()
    return HttpResponseRedirect("/processInfo/")


def get_All_JSON(request):
    json = "{'callers':["
    for caller in callers:
        json += str(callers[caller].get_All())
        json += ","
        callers[caller].clear_changes()
    json = json[:len(json)-1]
    json += "]}"
    return JsonResponse(json, safe=False)


def get_queue(request):
    caller_id = request.COOKIES.get('id')
    if request.method == "POST":
        form = DescForm(request.POST)
        if form.is_valid():
            try:
                lang = request.COOKIES.get('googtrans') #Cookie put in browser by google translate
            except:
                lang = '/en/en'
            callers[caller_id].add_language(lang)
            data = form.cleaned_data
            desc = translator.translate(data["desc"]).text
            callers[caller_id].add_description(desc)       
            
    # for caller in callers:
    #     print(callers[caller].id, callers[caller].description)
    form = DescForm()
    return render(request, "caller/queue.html", {"desc_form": form,"id":caller_id})



def update_caller_time(request):
    print(callers)
    caller_id = request.COOKIES.get('id')
    if(caller_id in url_to_send):
        url = url_to_send[caller_id]
        print(url)
    if request.method == "POST":
        caller_id = str(request.POST.get("id"))
        try:
            callers[caller_id].update_time()
        except:
            return HttpResponse(json.dumps({"url":"/"}), content_type='application/json')
    
    return HttpResponse(status=204)



def get_changes(request):
    to_remove=[]
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

    json = json[:len(json)-1]
    json += "]}"
    return JsonResponse(json, safe=False)

@csrf_exempt
def add_url(request):
    if request.method == "POST":
        url = request.POST['url']
        caller_id = request.POST['id']
        url_to_send[caller_id] = url
    return HttpResponse(status=204)
# to test curl -X post http://127.0.0.1:8000/addURLID/ -F "id=320493688610939527597112467216073650780" -F "url=google.com"