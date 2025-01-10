package com.markov.roomdb.presentation.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markov.roomdb.R
import com.markov.roomdb.data.model.Post
import com.markov.roomdb.databinding.FragmentAddBinding
import com.markov.roomdb.presentation.viewmodel.PostViewModel
import com.markov.roomdb.validateInputPost
import java.util.Calendar

class AddFragment : Fragment() {
    private lateinit var postViewModel: PostViewModel
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSavePost.setOnClickListener {
            savePost()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun savePost() {
        val title = binding.etPostTitle.text.toString()
        val content = binding.etPostContent.text.toString()
        val category = binding.etPostCategory.text.toString()
        val postImageUrl = binding.etPostImageUrl.text.toString()
        if (validateInputPost(title, content, category, postImageUrl)) {
            val publishedAt = Calendar.getInstance().time.toString()
            val post = Post(0, title, content, category, publishedAt, postImageUrl)
            postViewModel.addPost(post)
            Toast.makeText(requireContext(), "Post was successfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_AddFragment_to_ListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }
}