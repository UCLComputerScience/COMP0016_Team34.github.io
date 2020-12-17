from django import forms

class Dataform(forms.Form):
    firstname = forms.CharField(label='first name')
    surname = forms.CharField(label='surname')
    dob = forms.CharField(label='date of birth DD/MM/YYY')

