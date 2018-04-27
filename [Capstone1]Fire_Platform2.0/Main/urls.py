from django.conf.urls import url
from . import views
from django.views.generic import TemplateView
from django.contrib.auth.views import logout, login








urlpatterns = [
        url(r'^$', views.main_index, name='main_index'),
        url(r'^main/$', views.main_index, name='main_index'),
        url(r'^accounts/login/$', login, name='login'),
        url(r'^logout/$', logout, {'next_page': '/accounts/login/', }, name='logout_url'),
        url(r'^signup/$', views.signup, name='signup'),
        url(r'^mac/(?P<pk>\d+)/$',views.mac, name='mac'),
        url(r'^record/$',views.record, name='record'),
]

