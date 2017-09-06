from django.contrib import admin
from . import models
# Register your models here.

class FeedItemAdmin(admin.ModelAdmin):
	list_display = ('id', 'title', 'description')

admin.site.register(models.FeedItem,FeedItemAdmin)
