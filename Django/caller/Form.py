from django import forms
from .models import *


class Dataform(forms.Form):
    firstname = forms.CharField(widget=forms.Textarea(attrs={'id': 'first name', 'size': 'myfieldclass'}))
    
    surname = forms.CharField(label='Surname  ')
    dob = forms.CharField(label='Date of Birth (DD/MM/YYYY)  ')

class IDform(forms.Form):
    id = forms.CharField(label='id')

class DescForm(forms.Form):
    desc = forms.CharField(label="Description  ", widget=forms.Textarea(attrs={'cols': 80, 'rows': 10}))