package com.example.lab7_1.api

import com.example.lab7_1.GalleryItem
import com.google.gson.annotations.SerializedName

class FlickrResponse {
    var photos: PhotosResponse? = null // Change to nullable
//    @SerializedName("photo") var photo: GalleryItem? = null // Change to nullable
    var photo: PhotoResponse? = null // Change to nullable
}
