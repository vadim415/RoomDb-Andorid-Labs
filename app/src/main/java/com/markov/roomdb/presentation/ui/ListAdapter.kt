package com.markov.roomdb.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.markov.roomdb.R
import com.markov.roomdb.data.model.Post

class ListAdapter : RecyclerView.Adapter<ListAdapter.PostViewHolder>() {
    private var posts = emptyList<Post>()

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postTitle: TextView = view.findViewById(R.id.titleText)
        val postContent: TextView = view.findViewById(R.id.contentText)
        val postCategory: TextView = view.findViewById(R.id.categoryText)
        val postPublishedAt: TextView = view.findViewById(R.id.dateText)
        val postImage: ImageView = view.findViewById(R.id.postImage)
        val rowLayout: MaterialCardView = view.findViewById(R.id.row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)

        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.postTitle.text = post.title
        holder.postContent.text = post.content
        holder.postCategory.text = post.category
        holder.postPublishedAt.text = post.publishedAt
        Glide.with(holder.itemView.context)
            .load(post.image)
            .centerCrop()
            .placeholder(R.drawable.ic_image_placeholder)
            .error(R.drawable.ic_image_error)
            .into(holder.postImage)
        holder.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(post)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }
}