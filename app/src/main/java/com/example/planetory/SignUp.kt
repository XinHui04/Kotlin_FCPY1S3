package com.example.planetory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUp : AppCompatActivity() {

    lateinit var edtSEmail : EditText
    lateinit var SEmailContainer :TextInputLayout
    lateinit var edtSPsw : EditText
    lateinit var SPswContainer : TextInputLayout
    lateinit var edtSConfirmPsw : EditText
    lateinit var SConfirmPswContainer: TextInputLayout
    lateinit var btnSSubmit : Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Sign Up"

        edtSEmail = findViewById(R.id.edtSEmail)
        SEmailContainer = findViewById(R.id.SEmailContainer)
        edtSPsw = findViewById(R.id.edtSPsw)
        SPswContainer = findViewById(R.id.SPswContainer)
        edtSConfirmPsw = findViewById(R.id.edtSConfirmPsw)
        SConfirmPswContainer = findViewById(R.id.SConfirmPswContainer)
        btnSSubmit = findViewById(R.id.btnSSubmit)

        auth = Firebase.auth

        btnSSubmit.setOnClickListener{
            val xEmail = edtSEmail.text.toString().trim()
            val xPassword = edtSPsw.text.toString().trim()
            val yPassword = edtSConfirmPsw.text.toString().trim()

            SEmailContainer.isErrorEnabled = false
            SPswContainer.isErrorEnabled = false
            SConfirmPswContainer.isErrorEnabled = false

            if(xEmail.isNotEmpty() && xPassword.isNotEmpty() && yPassword.isNotEmpty()){

                if (xPassword == yPassword){
                    auth.createUserWithEmailAndPassword(xEmail,
                        xPassword).addOnCompleteListener { status ->
                        if(status.isSuccessful){
                            Toast.makeText(this,"Successfully Create An Account", Toast.LENGTH_SHORT).show()
                            val xx = Intent(this,LogIn::class.java)
                            startActivity(xx)
                        }else{
                            SEmailContainer.setError(status.exception!!.message)
                            edtSEmail.setText("")
                            edtSPsw.setText("")
                            edtSConfirmPsw.setText("")
                        }
                    }
                }else{
                    SConfirmPswContainer.setError("Password is not matching")
                    edtSEmail.setText("")
                    edtSPsw.setText("")
                    edtSConfirmPsw.setText("")
                }
            }else {
                Toast.makeText(this, "Please Fill in All the Field", Toast.LENGTH_SHORT).show()
                edtSEmail.setText("")
                edtSPsw.setText("")
                edtSConfirmPsw.setText("")
            }
        }
    }
}