package com.markov.roomdb.presentation.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.markov.roomdb.R
import com.markov.roomdb.data.model.Post
import com.markov.roomdb.databinding.FragmentUpdateBinding
import com.markov.roomdb.presentation.viewmodel.PostViewModel
import com.markov.roomdb.validateInputPost
import java.util.Calendar

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var postViewModel: PostViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.etUpdPostTitle.setText(args.currentPost.title)
        binding.etUpdPostContent.setText(args.currentPost.content)
        binding.etUpdPostCategory.setText(args.currentPost.category)
        binding.etUpdPostImageUrl.setText(args.currentPost.image)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUpdPost.setOnClickListener {
            updatePost()
        }
        binding.btnDeletePost.setOnClickListener {
            deletePost()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updatePost() {
        val title = binding.etUpdPostTitle.text.toString()
        val content = binding.etUpdPostContent.text.toString()
        val category = binding.etUpdPostCategory.text.toString()
        val postImageUrl = binding.etUpdPostImageUrl.text.toString()
        if (validateInputPost(title, content, category, postImageUrl)) {
            val publishedAt = Calendar.getInstance().time.toString()
            val updatedPost = Post(args.currentPost.id, title, content, category, publishedAt, postImageUrl)
            postViewModel.updatePost(updatedPost)
            Toast.makeText(requireContext(), "Post was successfully updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun deletePost() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {_, _ ->
            postViewModel.deletePost(args.currentPost)
            Toast.makeText(requireContext(), "The post was deleted", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        }
        builder.setNegativeButton("No") {_, _ ->}
        builder.setTitle("Delete ${args.currentPost.title}?")
        builder.setMessage("Are you sure you want to delete the post?")
        builder.create().show()
    }
}