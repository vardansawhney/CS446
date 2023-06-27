package com.example.amuse.ui.profile

data class Friend(var name: String, var email: String, var imageId: Int, var groups: Int){
    override fun toString(): String = name
}
