import json

from django.core import serializers
from django.forms import model_to_dict
from django.http import HttpResponse, JsonResponse

from server import models
from server.service import UserService
from server.utils import RR
def add(request):
    params = request.POST.dict()
    print(params)
    res=UserService.add(params)
    return RR.finish(res[0], res[1], res[2])

def update(request):
    params = request.POST.dict()
    res = {"code": 0, "msg": "success", "data": params}



def get(request):
    params = request.POST.dict()
    res = UserService.get(params)
    return RR.finish(0, "ok", res)

def validate(request):
    params = request.POST.dict()
    res = UserService.validate(params['phone'],params['password'])
    if res==False:
        return RR.finish(-1, "用户名或密码错误！", res)
    else:
        return RR.finish(0, "ok", res)

def getById(request):
    params = request.POST.dict()
    res = UserService.getById(params['id'])
    res['password']='invisiable'
    return RR.finish(0, "ok", res)