package com.markov.roomdb.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.markov.roomdb.R
import com.markov.roomdb.databinding.FragmentListBinding
import com.markov.roomdb.presentation.viewmodel.PostViewModel

class ListFragment : Fragment() {
    private lateinit var postViewModel: PostViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]
        postViewModel.posts.observe(viewLifecycleOwner, Observer { post ->
            adapter.setData(post)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAddPost.setOnClickListener {
            findNavController().navigate(R.id.action_ListFragment_to_AddFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}