from django.http.response import HttpResponse
from django.shortcuts import get_object_or_404, render
from django.http import HttpResponseRedirect
from django.urls import reverse
from django.views import generic
from django.utils import timezone

#from .models import Choice, Question
# import datetime


def get_home(request):
    html = "<html><p>hello</p></html>"
    return HttpResponse(html)