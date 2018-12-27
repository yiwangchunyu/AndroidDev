from django.db import models
from django.utils import timezone
from system.storage import ImageStorage

# Create your models here.
class User(models.Model):
    id = models.AutoField(primary_key=True)
    phone = models.CharField(max_length=20)
    nickname = models.CharField(max_length=32)
    password = models.CharField(max_length=32)
    gender = models.IntegerField(default=1)
    avatar = models.ImageField(null=True,blank=True,upload_to='avatar')
    status = models.IntegerField(default=1)
    ctime = models.DateTimeField(default= timezone.now)
    mtime = models.DateTimeField(auto_now=True)

class Dynamic(models.Model):
    id = models.AutoField(primary_key=True)
    user_id = models.CharField(max_length=20)
    content = models.CharField(max_length=2233)
    like = models.IntegerField(default=0)
    comment = models.IntegerField(default=0)
    post = models.IntegerField(default=0)
    img_count = models.IntegerField(default=0)
    img1 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img2 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img3 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img4 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img5 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img6 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img7 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img8 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    img9 = models.ImageField(null=True, blank=True, upload_to='dynamic_img/'+'%Y/%m/%d', storage=ImageStorage())
    status = models.IntegerField(default=1)
    ctime = models.DateTimeField(default= timezone.now)
    mtime = models.DateTimeField(auto_now=True)