from rest_framework import serializers
from . import models

class FeedItemSerializer(serializers.ModelSerializer):
	class Meta:
		model=models.FeedItem
		fields=('name','imageUrl','title','time','text','description')
