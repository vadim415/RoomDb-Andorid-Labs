package com.markov.roomdb.data.repository

import androidx.lifecycle.LiveData
import com.markov.roomdb.data.dao.PostDao
import com.markov.roomdb.data.model.Post

class PostRepositoryImpl(
    private val dao: PostDao
) : PostRepository {
    override suspend fun addPost(post: Post) {
        dao.addPost(post)
    }

    override suspend fun updatePost(post: Post) {
        dao.updatePost(post)
    }

    override fun getPosts(): LiveData<List<Post>> {
        return dao.getPosts()
    }

    override suspend fun getPostById(id: Int): Post? {
        return dao.getPostById(id)
    }

    override suspend fun deletePost(post: Post) {
        dao.deletePost(post)
    }
}