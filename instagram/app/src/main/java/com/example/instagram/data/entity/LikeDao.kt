package com.example.instagram.data.entity

import androidx.room.*

@Dao
interface LikeDao {
    @Insert
    fun insert(like: Like)

    @Update
    fun update(like: Like)

    @Delete
    fun delete(like: Like)

    @Query("SELECT * FROM LikeTable")
    fun getLike(): List<Like>

    @Query("SELECT likeState FROM LikeTable WHERE uid = :userId AND postId = :postId")
    fun getLikeState(userId: String, postId: Int): Boolean

    @Query("DELETE FROM LikeTable WHERE uid = :userId AND postId = :postId")
    fun deleteLikedPost(userId: String, postId: Int)

    @Query("UPDATE LikeTable SET likeState = :isLike WHERE uid = :userId AND postId = :postId")
    fun updateIsLikeById(isLike: Boolean, userId: String,  postId: Int)
}