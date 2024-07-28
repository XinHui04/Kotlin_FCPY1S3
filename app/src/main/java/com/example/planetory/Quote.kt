package com.example.planetory

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Quote : AppCompatActivity() {
    var position: Int=0

    lateinit var quoteslist:List<QuotesModel>

    @SuppressLint("MissingInflatedId")

    lateinit var txtQQuote: TextView
    lateinit var txtQAuthor: TextView
    lateinit var btnQNext: ImageView
    lateinit var btnQPrevious: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Quotes"

        txtQQuote = findViewById(R.id.txtQQuote)
        txtQAuthor = findViewById(R.id.txtQAuthor)
        btnQNext = findViewById(R.id.btnQNext)
        btnQPrevious = findViewById(R.id.btnQPrevious)

        ApiCall().getRandomQuotes(){listquote->
            if (listquote != null) {
                quoteslist=listquote
                txtQQuote.text=quoteslist.get(0).text
                txtQAuthor.text="~ "+quoteslist.get(0).author
            } else{
                Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
            }
        }

        btnQNext.setOnClickListener {
            nextQuote(txtQQuote,txtQAuthor)
        }
        btnQPrevious.setOnClickListener {
            previousQuote(txtQQuote,txtQAuthor)
        }
    }

    fun nextQuote(txtQQuote:TextView,txtQAuthor:TextView){
        if(position<quoteslist.size-1 && position>=0){
            position++;
            txtQQuote.text=quoteslist.get(position).text
            txtQAuthor.text="~ "+quoteslist.get(position).author
        } else{
            Toast.makeText(this,"You Reached the Last page",Toast.LENGTH_LONG).show()
        }
    }

    fun previousQuote(txtQQuote:TextView,txtQAuthor:TextView){
        if(position<quoteslist.size && position>0){
            position--;
            txtQQuote.text=quoteslist.get(position).text
            txtQAuthor.text="~ "+quoteslist.get(position).author
        } else{
            Toast.makeText(this,"You are on the 1st Page",Toast.LENGTH_LONG).show()
        }
    }
}