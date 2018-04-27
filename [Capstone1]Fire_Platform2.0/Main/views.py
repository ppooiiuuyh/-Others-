from django.shortcuts import render
from django.utils import timezone
from django import forms
#회원가입
from django.contrib.auth.models import User
from django.http import HttpResponseRedirect
from django.core.urlresolvers import reverse
from .forms import RegisterForm, MacForm
from django.shortcuts import render, redirect
from django.http import HttpResponse
from .forms import LoginForm
from django.contrib.auth.models import User
from django.contrib.auth import login, authenticate
from django.template import RequestContext
from django.utils.decorators import method_decorator
from django.contrib.auth.decorators import login_required
from django.template import Context
from django.template.loader import get_template
from .models import Test, SignUser
from django.shortcuts import get_object_or_404
from django.core.urlresolvers import reverse


def main_index(request):
    return render(request, 'Main/index.html', {})

def signup(request):
    """signsup
    to register users
    """
    if request.method == "POST":
        username = request.POST.get('username')
        password = request.POST.get('password')
        if User.objects.filter(username__exact=username).count():
            return HttpResponse('duplicate id', 400)
        else:
            user = User.objects.create_user(username, password=password)
            user.first_name = request.POST.get('name', '')
            user.save()
            signuser = SignUser()
            signuser.user = user
            signuser.save()
            macform = MacForm()
            return render(request, "registration/signup_next.html")

    elif request.method =="GET":
        userform = RegisterForm()

    return render(request, "registration/signup.html", {"userform": userform})


def login(request):
    if request.method == "POST":
        form = LoginForm(request.POST)
        username = request.POST['username']
        password = request.POST['password']
        user = authenticate(username = username, password = password)
        if user is not None:
            login(request, user)
            return redirect('index')
        else:
            return HttpResponse('로그인 실패. 다시 시도 해보세요.')
    else:
        form = LoginForm()
        return render(request, 'registration/mac.html', {'form': form})


def mac(request, pk):
    signUser = get_object_or_404(SignUser, pk=pk)
    if request.method == "POST":
        macform = MacForm(request.POST, instance=signUser)
        if macform.is_valid():
            signUser = macform.save(commit=False)
            signUser.user = request.user
            macform.save()
            return render(request,'main/index.html', {"macform": macform})
    else:
        macform= MacForm(instance=signUser)
    return render(request, 'registration/mac.html', {"macform": macform})

def record(request):
    test = Test.objects.all()
    return render(request, 'fire/record.html',  {'test': test})

