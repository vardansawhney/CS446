package com.example.amuse.ui.profile

data class Group(var name: String, var minMembers: Int, var maxMembers: Int,
                 var solo: Boolean, var invited: ArrayList<Friend>, var accepted: ArrayList<Friend>)
