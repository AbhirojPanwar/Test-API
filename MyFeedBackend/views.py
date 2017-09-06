from rest_framework import generics
from . import serializers,models

class FeedItemList(generics.ListAPIView):
	serializer_class=serializers.FeedItemSerializer
	queryset=models.FeedItem.objects.all()

