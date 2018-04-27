from django import forms
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm
from .models import AuthUser
from .models import SignUser



class RegisterForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['username', 'email', 'password']
        widgets = {
            'username': forms.TextInput(attrs={'class': 'form-control', 'placeholder': '15자 이내로 입력 가능합니다.'}),
            'email': forms.EmailInput(attrs={'class': 'form-control'}),
            'password': forms.PasswordInput(attrs={'class': 'form-control'}),
        }
        labels = {
            'username': '닉네임',
            'email': '이메일',
            'password': '패스워드',
        }

class LoginForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['username', 'password'] # 로그인 시에는 유저이름과 비밀번호만 입력 받는다.

class MacForm(forms.ModelForm):
    class Meta:
        model = SignUser
        fields = ['mac_add']
        widgets = {
            'mac_add': forms.TextInput(attrs={'class': 'form-control'}),
        }
        labels = {
            'mac_add': 'Mac 주소를 입력해주세요',
        }