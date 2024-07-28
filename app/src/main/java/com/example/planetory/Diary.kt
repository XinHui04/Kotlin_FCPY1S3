package com.example.planetory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class Diary : AppCompatActivity() {
    lateinit var btnAddDiary: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var diaryAdapter: DiaryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Diary"

        btnAddDiary = findViewById(R.id.btnAddDiary)
        recyclerView = findViewById(R.id.recyclerView)

        btnAddDiary.setOnClickListener {
            var intent = Intent(this, DiaryDetails::class.java)
            startActivity(intent)
        }

        setupRecyclerView()

    }

    fun setupRecyclerView(){
        var query: Query
        query = getCollectionReferenceForDiaries().orderBy("timestamp",Query.Direction.DESCENDING)

        val options = FirestoreRecyclerOptions.Builder<Note>()
            .setQuery(query, Note::class.java)
            .build()

        recyclerView.layoutManager = LinearLayoutManager(this)
        diaryAdapter = DiaryAdapter(options, this)
        recyclerView.adapter = diaryAdapter
    }

    fun getCollectionReferenceForDiaries(): CollectionReference {
        return FirebaseFirestore.getInstance().collection("diaries")
    }

    override fun onStart() {
        super.onStart()
        diaryAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        diaryAdapter.stopListening()
    }

    override fun onResume() {
        super.onResume()
        diaryAdapter.notifyDataSetChanged()
    }
}