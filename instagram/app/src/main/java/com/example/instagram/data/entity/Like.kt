package com.example.instagram.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LikeTable")
data class Like (
    var uid: String,
    var postId : Int,
    var likeState : Boolean = false
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

