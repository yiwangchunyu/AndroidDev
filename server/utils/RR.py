from django.http import HttpResponse, JsonResponse

def finish(code, msg, data):
    return JsonResponse({"code":code,"msg":msg, "data":data})