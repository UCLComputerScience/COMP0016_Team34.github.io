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



def get_home(request):
    return render(request,"caller/index.html")

def get_JSON(request):
    c = Caller("Bob Smith","01/01/2000");
    c.add_description("My Knee Hurts")
    return JsonResponse(c.to_JSON(),safe=False)
