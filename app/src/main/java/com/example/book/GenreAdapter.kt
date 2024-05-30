package com.example.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class GenreAdapter(private val genres: List<Genre>, private val onGenreSelected: (Genre) -> Unit) :
    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    private var selectedGenre: Genre? = genres.firstOrNull()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.genreText.text = genre.displayName
        holder.itemView.setBackgroundResource(
            if (genre == selectedGenre) R.drawable.genre_selected_background
            else R.drawable.genre_default_background
        )
        holder.genreText.setTextColor(
            if (genre == selectedGenre) ContextCompat.getColor(holder.itemView.context, R.color.selected_genre_text_color)
            else ContextCompat.getColor(holder.itemView.context, R.color.default_genre_text_color)
        )
        holder.genreText.setOnClickListener {
            selectedGenre = genre
            notifyDataSetChanged()
            onGenreSelected(genre)
        }
    }

    override fun getItemCount() = genres.size

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genreText: TextView = itemView.findViewById(R.id.genreText)
    }
}
