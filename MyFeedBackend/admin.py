from django.contrib import admin
from . import models
# Register your models here.

class FeedItemAdmin(admin.ModelAdmin):
	list_display = ('id', 'title', 'name')

admin.site.register(models.FeedItem,FeedItemAdmin)
