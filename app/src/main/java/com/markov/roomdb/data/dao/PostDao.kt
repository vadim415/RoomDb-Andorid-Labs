package com.markov.roomdb.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.markov.roomdb.data.model.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPost(post: Post)

    @Update
    suspend fun updatePost(post: Post)

    @Query("select * from posts")
    fun getPosts(): LiveData<List<Post>>

    @Query("select * from posts where id = :id")
    suspend fun getPostById(id: Int): Post?

    @Delete
    suspend fun deletePost(post: Post)
}