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
from .Form import Dataform

callers = []

def get_home(request):
    if request.method == "POST":
        form = Dataform(request.POST)
        if form.is_valid():
            data = form.cleaned_data
            c = Caller(data["firstname"]+data["surname"],data["dob"])
            callers.append(c)
            return HttpResponseRedirect("/getJSON/") 
    else:
        form = Dataform()
    return render(request,"caller/index.html",{"form":form})

def get_JSON(request):
    json = "{'callers':["
    for caller in callers:
        json+=str(caller.to_JSON())
        json +=","
    json= json[:len(json)-1]
    json+="]}"
    return JsonResponse(json,safe=False)

def get_static_JSON(request):
    c = Caller("Bob Smith","11/1/2001")
    c.add_description("My leg is broken")
    return JsonResponse(c.to_JSON(),safe=False)
        
