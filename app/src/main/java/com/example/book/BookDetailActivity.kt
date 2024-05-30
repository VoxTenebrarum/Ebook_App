package com.example.book

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)


        // Получение данных, переданных из MainActivity
        val bookTitle = intent.getStringExtra("BOOK_TITLE")
        val bookAuthor = intent.getStringExtra("BOOK_AUTHOR")
        val bookImageResId = intent.getIntExtra("BOOK_IMAGE", 0)
        val bookRating = intent.getFloatExtra("BOOK_RATING", 0f)
        val bookGenres = intent.getStringArrayListExtra("BOOK_GENRES")

        // Найти и установить значения в соответствующие элементы UI
        findViewById<TextView>(R.id.bookTitle)?.text = bookTitle
        findViewById<TextView>(R.id.bookAuthor)?.text = bookAuthor
        findViewById<ImageView>(R.id.bookImage)?.setImageResource(bookImageResId)
        findViewById<TextView>(R.id.bookRating)?.text = bookRating.toString()
        val backArrowImageView = findViewById<ImageView>(R.id.backArrow)
        val readButton: TextView = findViewById(R.id.readButton)
        readButton.setOnClickListener {
            val intent = Intent(this@BookDetailActivity, ReadActivity::class.java)
            startActivity(intent)
        }

        // Удалить последний жанр из списка
        bookGenres?.let {
            if (it.isNotEmpty()) {
                it.removeAt(it.size - 1)
            }

            // Установить адаптер для RecyclerView
            val genreRecyclerView = findViewById<RecyclerView>(R.id.genreRecyclerView)
            genreRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            genreRecyclerView.adapter = GenreRecyclerView(it)
        }

        backArrowImageView.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
    }

