from django.urls import path

from . import views

app_name = 'caller'
urlpatterns = [
    path('', views.get_home, name='index'),
    path('getJSON/', views.get_JSON, name='json'),
    path('processInfo/',views.get_home, name='processInfo'),
    path('staticInfo/',views.get_static_JSON, name='staticInfo'),
    path('clearData/',views.clear_data, name='clearData'),
    path('getAll/',views.get_All_JSON, name='getAll'),
    path('getChanges/',views.get_changes,name='changes'),
    path('queue/',views.get_queue, name='queue'),
    path('updateCaller/',views.update_caller_time,name='updateCallerTime'),
    path('addURLID/',views.add_url,name="addURLToSend"),
]
