from django import forms

class Dataform(forms.Form):
    firstname = forms.CharField(label='first name')
    surname = forms.CharField(label='surname')
    dob = forms.CharField(label='date of birth DD/MM/YYYY')

class IDform(forms.Form):
    id = forms.CharField(label='id')

class DescForm(forms.Form):
    desc = forms.CharField(label="description")