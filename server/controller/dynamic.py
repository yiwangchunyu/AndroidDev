import os

from server.models import Dynamic
from server.service import UserService
from server.utils import RR
from server.service import DynamicService
from AndroidFinalService import settings

def create(request):
    print(request.POST)
    if request.POST:
        dict={
            "img1": None, "img2": None, "img3": None,
            "img4": None, "img5": None, "img6": None,
            "img7": None, "img8": None, "img9": None
        }
        dict['user_id']=request.POST['user_id']
        dict['content']=request.POST['content']
        dict['img_count']=request.POST['img_count']
        if int(request.POST['img_count'])>0:
            img_list = request.FILES.getlist("imgs")
            for i,img in enumerate(img_list):
                dict['img'+str(i+1)]=img
        dynamic = Dynamic(**dict)
        print(dict)
        dynamic.save()
        print(dict)
    return RR.finish(0,"ok",[])