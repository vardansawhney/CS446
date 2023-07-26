package com.example.amuse.ui.profile

import android.graphics.Bitmap
import android.net.Uri

data class Friend(var name: String, var email: String, var imageId: Int){
    override fun toString(): String = name
}
