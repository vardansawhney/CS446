package com.example.amuse.ui.profile

class Upload {
    var name: String? = null
    var email: String? = null
    var imageUrl: String? = null

    constructor() {
        //empty constructor needed
    }

    constructor(name: String, email:String, imageUrl: String?) {
        var name = name
        if (name.trim { it <= ' ' } == "") {
            name = "No Name"
        }
        var email = email
        if (name.trim { it <= ' ' } == "") {
            name = "No Email"
        }
        this.name = name
        this.email = email
        this.imageUrl = imageUrl
    }
}