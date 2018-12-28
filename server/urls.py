from django.conf.urls import url
from django.conf.urls.static import static

from AndroidFinalService import settings
from server.controller import user, dynamic

urlpatterns = [
    url(r'^user/add$', user.add),
    url(r'^user/update$', user.update),
    url(r'^user/get$', user.get),
    url(r'^user/getById$', user.getById),
    url(r'^user/validate$', user.validate),
    url(r'^dynamic/create$', dynamic.create),
    url(r'^dynamic/getAll$', dynamic.getAll),
]
