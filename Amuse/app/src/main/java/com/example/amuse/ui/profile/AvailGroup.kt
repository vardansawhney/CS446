package com.example.amuse.ui.profile

data class AvailGroup(var name: String, var startTime: String, var endTime: String, var date: String, var availSpotsLeft: Int)

//data class AvailGroup(var name: String, var startTime: String, var endTime: String, var date: String,
//                      var minMembers: Int, var maxMembers: Int, var accepted: ArrayList<Friend>, var availSpotsLeft: Int)

//data class Group(var name: String, var minMembers: Int, var maxMembers: Int,
//                 var solo: Boolean, var invited: ArrayList<Friend>, var accepted: ArrayList<Friend>)