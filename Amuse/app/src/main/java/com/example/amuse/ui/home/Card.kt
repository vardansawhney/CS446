package com.example.amuse.ui.home

data class Card(var title: String, var location: String, var stars: String,var desc:String = "",
                var price: String, var groups: String? = null, var likes: String? = null, var imageId: Int? = null)