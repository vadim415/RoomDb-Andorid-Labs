package com.markov.roomdb.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.markov.roomdb.data.db.PostDatabase
import com.markov.roomdb.data.model.Post
import com.markov.roomdb.data.repository.PostRepository
import com.markov.roomdb.data.repository.PostRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {
    val posts: LiveData<List<Post>>
    private val repository: PostRepository

    init {
        val dao = PostDatabase.getDatabase(application).dao()
        repository = PostRepositoryImpl(dao)
        posts = repository.getPosts()
    }

    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePost(post)
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePost(post)
        }
    }
}