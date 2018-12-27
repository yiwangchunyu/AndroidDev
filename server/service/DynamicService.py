import json

from django.core import serializers
from django.forms import model_to_dict
from django.http import HttpResponse, JsonResponse

from server import models

def create(user_id,content,img_list):
    return