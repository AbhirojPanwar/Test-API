from django.db import models

# Create your models here.

class FeedItem(models.Model):
	name=models.CharField(max_length=100,blank=False)
	imageUrl=models.URLField(blank=True)
	title=models.CharField(max_length=100,blank=False)
	text=models.CharField(max_length=500,blank=True)
	description=models.TextField()
	time=models.TextField(blank=False)
