import json

from django.core import serializers
from django.forms import model_to_dict
from django.http import HttpResponse, JsonResponse

from server import models


def add(params):
    if userExist(params['phone']):
        return [-1,"用户已存在",[]]
    res = models.User.objects.create(**params)
    return [0,"ok",{"id":res.id}]

def update(params):
    return []


def get(params):
    user = models.User.objects.filter(**params)
    json_data = serializers.serialize('json', user)
    json_data = json.loads(json_data)
    return json_data

def userExist(phone):
    user = models.User.objects.filter(phone__exact=phone)
    user_list = list(user)
    if len(user_list)>0:
        return True
    else:
        return False

def validate(phone, password):
    user = models.User.objects.filter(phone=phone, password=password).values()
    if len(user) > 0:
        return list(user)
    else:
        return False

def getById(id):
    user = models.User.objects.filter(id=id).values()
    user = list(user)
    return user[0]