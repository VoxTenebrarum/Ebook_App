package com.example.book

import android.os.Bundle
import android.util.TypedValue
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {
    var selectedGenre: Genre? = null
    var currentQuery: String = ""

    private lateinit var bookAdapter: BookAdapter
    private lateinit var noBooksTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView = findViewById<SearchView>(R.id.search)
        val searchEditTextId = resources.getIdentifier("android:id/search_src_text", null, null)
        val searchEditText = searchView.findViewById<EditText>(searchEditTextId)
        searchEditText.setHintTextColor(ContextCompat.getColor(this, R.color.black))
        searchEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12f)

        noBooksTextView = findViewById(R.id.noBooksTextView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                currentQuery = newText
                bookAdapter.filterBySearchAndGenre(currentQuery, selectedGenre)
                return true
            }
        })


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        bookAdapter = BookAdapter(getBooks(), noBooksTextView)
        recyclerView.adapter = bookAdapter

        val genreRecyclerView: RecyclerView = findViewById(R.id.genreRecyclerView)
        genreRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        genreRecyclerView.adapter = GenreAdapter(Genre.values().toList()) { genre ->
            selectedGenre = genre
            bookAdapter.filterBySearchAndGenre(currentQuery, selectedGenre)
        }

    }

    private fun getBooks(): List<Book> {
        return listOf(
            Book(
                "Shōgun",
                "James Clavell",
                R.drawable.shogun,
                4.41f,
                listOf(Genre.Historical, Genre.Classics, Genre.All)
            ),
            Book(
                "Memoirs of a Geisha",
                "Arthur Golden",
                R.drawable.memoirs_of_a_geisha,
                4.15f,
                listOf(Genre.Historical, Genre.Romance, Genre.All)
            ),
            Book(
                "1984",
                "George Orwell",
                R.drawable.book1984,
                4.19f,
                listOf(Genre.Fantasy, Genre.Classics, Genre.All)
            ),
            Book(
                "Romeo and Juliet",
                "William Shakespeare",
                R.drawable.romeo_and_juliet,
                3.74f,
                listOf(Genre.Historical, Genre.Classics, Genre.All)
            ),
            Book(
                "The Martian",
                "Andy Weir",
                R.drawable.the_martian,
                4.42f,
                listOf(Genre.ScienceFiction, Genre.All)
            ),
            Book(
                "The Road",
                "Cormac McCarthy",
                R.drawable.the_road,
                3.99f,
                listOf(Genre.ScienceFiction, Genre.Horror, Genre.All)
            ),
            Book(
                "Death in the Spires",
                "K.J. Charles",
                R.drawable.death_in_the_spires,
                4.49f,
                listOf(Genre.Historical, Genre.Romance, Genre.Detective, Genre.All)
            ),
            Book(
                "Angelfall",
                "Susan Ee",
                R.drawable.angelfall,
                4.11f,
                listOf(Genre.Fantasy, Genre.Romance, Genre.All)
            ),
            Book(
                "It",
                "Stephen King",
                R.drawable.it,
                4.24f,
                listOf(Genre.Horror, Genre.Fantasy, Genre.Classics, Genre.All)
            ),
            Book(
                "Last Night",
                "Luanne Rice",
                R.drawable.last_night,
                4.06f,
                listOf(Genre.Detective, Genre.All)
            ),
            Book(
                "Hyperion",
                "Dan Simmons",
                R.drawable.hyperion,
                4.26f,
                listOf(Genre.ScienceFiction, Genre.Fantasy, Genre.Horror, Genre.All)
            )
        )
    }
}

data class Book(
    val title: String,
    val author: String,
    val imageResId: Int,
    val rating: Float,
    val genres: List<Genre> //Роман Фантастика Детектив Фентезі Історичні Хоррор Класика
)
