package com.example.amuse.EventsBackend

import com.example.amuse.GroupsBackend.Group
import com.google.gson.Gson

data class Contacts(val phoneNumber: Int, val website: String?, val email: String?, val socials: List<Pair<String,String>>?){
    var _phoneNumber = phoneNumber;
    var _website = website;
    var _email = email;
    var _socials = socials;

    //TODO: add code to parse socials and do some preprocessing (associate icon...etc)
}
//TODO:type might need to change
class Event(eventJsonInfo: Gson) {
    private lateinit var availableGroups: List<Group>;
    private var numInterested: Int = 0;
    private lateinit var contacts : Contacts;
    private lateinit var reviews : List<String>;
    private var rating: Int = 0;
    var price: Int = 0;
    var distance: Float = 0.toFloat();
    private var eventDescription :String= "";
    lateinit var types : List<String>;
    init {
        //TODO: once you find out how data is formatted in db resp, init fields
    }
}