from django.contrib import admin
from .models import Test
from django.contrib.auth.admin import UserAdmin
from django.contrib.auth.models import User
from .models import SignUser


class SignUserinline(admin.StackedInline):
    model = SignUser
    can_delete = False
    verbose_name_plural = 'Mac Address'


# Define a new User admin
class UserAdmin(UserAdmin):
    inlines = (SignUserinline,)


# Re-register UserAdmin
admin.site.unregister(User)
admin.site.register(User, UserAdmin)

admin.site.register(Test)