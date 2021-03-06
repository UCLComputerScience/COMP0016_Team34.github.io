from django import forms
from .models import *


class Dataform(forms.Form):
    firstname = forms.CharField(label='Firstname')
    
    surname = forms.CharField(label='Surname  ')
    dob = forms.CharField(label='Date of Birth (DD/MM/YYYY)  ')

class IDform(forms.Form):
    id = forms.CharField(label='id')

class DescForm(forms.Form):
    desc = forms.CharField(label="Description  ", widget=forms.Textarea(attrs={'cols': 80, 'rows': 10, 'padding': 20}))