package com.example.lab7_1.api

import com.example.lab7_1.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotosResponse {
    @SerializedName("photo") lateinit var galleryItems: List<GalleryItem>
}