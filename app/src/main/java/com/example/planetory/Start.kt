package com.example.planetory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Start : AppCompatActivity() {

    lateinit var btnSsignUp: Button
    lateinit var btnSlogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnSsignUp = findViewById(R.id.btnSsignUp)
        btnSlogin = findViewById(R.id.btnSlogin)

        btnSsignUp.setOnClickListener {
            var intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnSlogin.setOnClickListener {
            var intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }
}
