from django.urls import path

from . import views

app_name = 'caller'
urlpatterns = [
    path('', views.get_warning_screen, name='index'),
    path('getJSON/', views.get_JSON, name='json'),
    path('processInfo/',views.get_home, name='processInfo'),
    path('staticInfo/',views.get_static_JSON, name='staticInfo'),
    path('clearData/',views.clear_data, name='clearData'),
    path('getAll/',views.get_All_JSON, name='getAll'),
    path('getChanges/',views.get_changes,name='changes'),
    path('queue/',views.get_queue, name='queue'),
    path('updateCaller/',views.update_caller_time,name='updateCallerTime'),
    path('addURLID/',views.add_url,name="addURLToSend"),
    path('login/',views.login_view,name="login"),
    path('loginTest/',views.is_logged_in,name="isLoggedin"),
    path('logout/',views.logout_view,name="logout"),
    path('position/',views.get_queue_position,name="position"),
    path('news/',views.get_news_screen,name="news"),
    path('warning/',views.get_warning_screen,name="warning"),
    path('cookieWarning/',views.get_cookie_warning_screen,name="Cookiewarning"),
    path('links/',views.show_links,name='links')
]
