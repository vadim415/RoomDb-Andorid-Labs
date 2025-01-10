package com.markov.roomdb.data.repository

import androidx.lifecycle.LiveData
import com.markov.roomdb.data.model.Post

interface PostRepository {
    suspend fun addPost(post: Post)

    suspend fun updatePost(post: Post)

    fun getPosts(): LiveData<List<Post>>

    suspend fun getPostById(id: Int): Post?

    suspend fun deletePost(post: Post)
}