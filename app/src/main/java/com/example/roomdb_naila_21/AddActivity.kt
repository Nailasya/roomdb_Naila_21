package com.example.roomdb_naila_21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roomdb_naila_21.room.Constant
import com.example.roomdb_naila_21.room.Movie
import com.example.roomdb_naila_21.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDb(this) }
    private var MovieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupView()
        setupListener()

    }

    fun setupView(){
        val intentType = intent.getIntExtra("intent_type", 0)
        when (intentType){
            Constant.TYPE_CREATE -> {

            }
            Constant.TYPE_READ -> {
                btn_save.visibility = View.GONE
            }
        }
    }

    fun setupListener(){
        btn_save.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch {
                    db.movieDao().addMovie(
                        Movie(0,et_title.text.toString(), et_description.text.toString())
                    )
                    finish()
                }
            }
        }

        fun getMovies(){
            MovieId = intent.getIntExtra("intent_id", 0)
            CoroutineScope(Dispatchers.IO).launch {
                val movies = db.movieDao().getMovies(  )[0]
                et_title.setText( movies.title )
                et_description.setText( movies.desc)
            }
        }
    }
