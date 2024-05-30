package com.example.book

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private var books: List<Book>, private val noBooksTextView: TextView) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var filteredBooks: List<Book> = books

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_card, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = filteredBooks[position]
        holder.bookTitle.text = book.title
        holder.bookAuthor.text = book.author
        holder.bookImage.setImageResource(book.imageResId)
        holder.bookRating.text = book.rating.toString()

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BookDetailActivity::class.java).apply {
                putExtra("BOOK_TITLE", book.title)
                putExtra("BOOK_AUTHOR", book.author)
                putExtra("BOOK_IMAGE", book.imageResId)
                putExtra("BOOK_RATING", book.rating)
                putStringArrayListExtra("BOOK_GENRES", ArrayList(book.genres.map { it.displayName }))
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = filteredBooks.size

    fun filterBySearchAndGenre(query: String, selectedGenre: Genre?) {
        filteredBooks = if (query.isEmpty() && selectedGenre == null) {
            books
        } else {
            books.filter { book ->
                val titleContainsQuery = book.title.contains(query, ignoreCase = true)
                val authorContainsQuery = book.author.contains(query, ignoreCase = true)
                val genreMatches = selectedGenre == null || book.genres.contains(selectedGenre)
                (titleContainsQuery || authorContainsQuery) && genreMatches
            }
        }
        notifyDataSetChanged()
        noBooksTextView.visibility = if (filteredBooks.isEmpty()) View.VISIBLE else View.GONE
    }


    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookImage: ImageView = itemView.findViewById(R.id.bookImage)
        val bookTitle: TextView = itemView.findViewById(R.id.bookTitle)
        val bookAuthor: TextView = itemView.findViewById(R.id.bookAuthor)
        val bookRating: TextView = itemView.findViewById(R.id.bookRating)
    }
}

