package com.example.planetory

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class Menu : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var btnImgTDL: ImageButton
    lateinit var btnImgDiary: ImageButton
    lateinit var btnImgPomo: ImageButton
    lateinit var btnImgQuote: ImageButton
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        supportActionBar!!.title = "Menu"

        btnImgTDL = findViewById(R.id.btnImgTDL)
        btnImgDiary = findViewById(R.id.btnImgDiary)
        btnImgPomo = findViewById(R.id.btnImgPomo)
        btnImgQuote = findViewById(R.id.btnImgQuote)
        auth = Firebase.auth

        btnImgTDL.setOnClickListener{
            var intent = Intent(this, ToDoList::class.java)
            startActivity(intent)
        }

        btnImgDiary.setOnClickListener{
            var intent = Intent(this, Diary::class.java)
            startActivity(intent)
        }

        btnImgPomo.setOnClickListener{
            var intent = Intent(this, Pomodoro::class.java)
            startActivity(intent)
        }

        btnImgQuote.setOnClickListener {
            var intent = Intent(this, Quote::class.java)
            startActivity(intent)
        }

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView :NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.nav_info -> startActivity(Intent(applicationContext, AboutUs::class.java))
                R.id.nav_setting -> startActivity(Intent(applicationContext, Setting::class.java))
                R.id.nav_signUp -> startActivity(Intent(applicationContext, SignUp::class.java))
                R.id.nav_login -> startActivity(Intent(applicationContext, LogIn::class.java))
                R.id.nav_logout ->{
                    Toast.makeText(applicationContext,"Successfully Log Out",Toast.LENGTH_LONG).show()
                    auth.signOut()
                    startActivity(Intent(applicationContext, Start::class.java))
                }
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}