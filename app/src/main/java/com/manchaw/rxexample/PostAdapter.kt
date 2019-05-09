package com.manchaw.rxexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostAdapter(var context: Context, var list: List<PostResponse>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false))

//       LayoutInflater.from(parent.context).inflate(R.layout.post)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindTo(list[position])

        holder.itemView.setOnClickListener {
            Toast.makeText(context, it.title.text, Toast.LENGTH_SHORT).show()
        }
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(post: PostResponse) {
            itemView.title.text = post.title
            itemView.author.text = post.userId.toString()
            itemView.body.text = post.body


        }
    }
}