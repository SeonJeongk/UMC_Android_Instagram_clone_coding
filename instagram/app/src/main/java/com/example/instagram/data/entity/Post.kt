package com.example.instagram.data.entity
data class Post(
    var post_writer_name : String,
    var post_writer_username :String,
    var post_writer_profile_img :String,
    var post_contnent:String,
    var post_like:String,
    var post_img:Array<String>,
)
