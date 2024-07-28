package com.example.planetory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LogIn : AppCompatActivity() {

    private lateinit var LEmailContainer: TextInputLayout
    private lateinit var edtLEmail: EditText
    private lateinit var LPswContainer : TextInputLayout
    private lateinit var edtLPsw : EditText
    private lateinit var btnLSubmit: Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Log In"

        LEmailContainer = findViewById(R.id.LEmailContainer)
        edtLEmail = findViewById(R.id.edtLEmail)
        LPswContainer = findViewById(R.id.LPswContainer)
        edtLPsw = findViewById(R.id.edtLPsw)
        btnLSubmit = findViewById(R.id.btnLSubmit)
        auth = Firebase.auth

        btnLSubmit.setOnClickListener{
            val xEmail = edtLEmail.text.toString().trim()
            val xPassword = edtLPsw.text.toString().trim()

            LEmailContainer.isErrorEnabled = false
            LPswContainer.isErrorEnabled = false

            if (xEmail.isNotEmpty() && xPassword.isNotEmpty()){
                auth.signInWithEmailAndPassword(xEmail,
                    xPassword).addOnCompleteListener {status ->
                        if (status.isSuccessful){
                          Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show()
                          val xx = Intent(this,Menu::class.java)
                          startActivity(xx)
                        }else{
                            Toast.makeText(this, "Please Sign Up For New User. Ignore if you already registered", Toast.LENGTH_LONG).show()
                            if (!Patterns.EMAIL_ADDRESS.matcher(edtLEmail.getText().toString()).matches()){
                                LEmailContainer.setError("Invalid Email")
                                edtLEmail.setText("")
                                edtLPsw.setText("")
                            }else{
                                LPswContainer.setError("Wrong Password")
                                edtLEmail.setText("")
                                edtLPsw.setText("")
                            }
                        }
                }
            }else {
                Toast.makeText(this, "Please Fill in All the Field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
