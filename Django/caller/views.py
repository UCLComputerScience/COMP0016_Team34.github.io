from json.encoder import JSONEncoder
from django.http.response import HttpResponse, JsonResponse
from django.shortcuts import get_object_or_404, render
from django.http import HttpResponseRedirect
from django.urls import reverse
from django.views import generic
from django.utils import timezone

# from .models import CallerModel
# import datetime

from .Caller import Caller
from .Form import Dataform, IDform

import uuid

callers = {}

def get_home(request):
    if request.method == "POST":
        form = Dataform(request.POST)
        if form.is_valid():
            data = form.cleaned_data
            caller_id = uuid.uuid4().int # generates a unique id
            response = HttpResponseRedirect("/queue/")
            response.set_cookie("id",str(caller_id))
            c = Caller(data["firstname"]+" "+data["surname"],data["dob"],caller_id)
            callers[str(caller_id)] = c
            return response
    else:
        form = Dataform()
    return render(request,"caller/index.html",{"form":form})

def get_JSON(request):
    to_remove = []
    json = "{'callers':["
    for caller in callers:
        caller_json = callers[caller].to_JSON()
        if(len(caller_json) != 1):
            json+=str(caller_json)
            json +=","
            callers[caller].clear_changes()
            if "'active': False" in json:
                to_remove.append(caller)
    json= json[:len(json)-1]
    json+="]}"
    for caller in to_remove:
        del callers[caller]
    return JsonResponse(json,safe=False)

def get_static_JSON(request):
    c = Caller("Bob Smith","11/1/2001")
    c.add_description("My leg is broken")
    return JsonResponse(c.to_JSON(),safe=False)

def clear_data(request):
    callers.clear()
    return HttpResponseRedirect("/processInfo/") 

def get_All_JSON(request):
    json = "{'callers':["
    for caller in callers:
        json+=str(callers[caller].get_All())
        json +=","
        callers[caller].clear_changes()
    json= json[:len(json)-1]
    json+="]}"
    return JsonResponse(json,safe=False)

def get_queue(request):
    print(callers)
    if request.method == "POST":
        form = IDform(request.POST)
        if form.is_valid():
            data = form.cleaned_data
            caller_id = data["id"]
            try:
                callers[caller_id].update_time()
            except:
                return HttpResponseRedirect("..") 

                
    caller_id = request.COOKIES.get('id')
    form = IDform(initial={'id': caller_id})
    return render(request,"caller/queueProxy.html",{"id":caller_id,"form":form})


