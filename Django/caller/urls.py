from django.urls import path

from . import views

app_name = 'caller'
urlpatterns = [
    path('', views.get_home, name='index'),
    path('getJSON/', views.get_JSON, name='json'),
    path('processInfo/',views.get_home, name='json'),
    path('staticInfo/',views.get_static_JSON, name='json'),
    path('clearData/',views.clear_data, name='json'),
    path('getAll/',views.get_All_JSON, name='json')
    # path('<int:pk>/', views.DetailView.as_view(), name='detail'),
    # path('<int:pk>/results/', views.ResultsView.as_view(), name='results'),
    # path('<int:question_id>/vote/', views.vote, name='vote'),
]
