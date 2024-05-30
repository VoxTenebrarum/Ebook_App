package com.example.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class GenreRecyclerView(private val genres: List<String>) : RecyclerView.Adapter<GenreRecyclerView.GenreViewHolder>() {

    class GenreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreTextView: TextView = view.findViewById(R.id.genreText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.genreTextView.text = genres[position]
        holder.itemView.setBackgroundResource(
             R.drawable.genre_detail_bg
        )
    }

    override fun getItemCount() = genres.size
}
