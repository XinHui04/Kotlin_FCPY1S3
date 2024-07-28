package com.example.planetory

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class Setting : AppCompatActivity() {

    lateinit var CEmailContainer : TextInputLayout
    lateinit var OldPswContainer : TextInputLayout
    lateinit var NewPswContainer : TextInputLayout
    lateinit var CConfirmPswContainer : TextInputLayout
    lateinit var edtCEmail : EditText
    lateinit var edtCOldPsw : EditText
    lateinit var edtCNewPsw : EditText
    lateinit var edtCConfirmPsw : EditText
    lateinit var btnCSubmit: Button
    lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Setting"

        CEmailContainer = findViewById(R.id.CEmailContainer)
        OldPswContainer = findViewById(R.id.OldPswContainer)
        NewPswContainer = findViewById(R.id.NewPswContainer)
        CConfirmPswContainer = findViewById(R.id.CConfirmPswContainer)
        edtCEmail = findViewById(R.id.edtCEmail)
        edtCOldPsw = findViewById(R.id.edtCOldPsw)
        edtCNewPsw = findViewById(R.id.edtCNewPsw)
        edtCConfirmPsw = findViewById(R.id.edtCConfirmPsw)
        btnCSubmit = findViewById(R.id.btnCSubmit)
        auth = Firebase.auth

        val user = Firebase.auth.currentUser
        user?.let {
            val email = it.email
            edtCEmail.setText(email)

        btnCSubmit.setOnClickListener {

            val xEmail = edtCEmail.text.toString().trim()
            val xPassword = edtCOldPsw.text.toString().trim()
            val yPassword = edtCNewPsw.text.toString().trim()
            val zPassword = edtCConfirmPsw.text.toString().trim()

            CEmailContainer.isErrorEnabled = false
            OldPswContainer.isErrorEnabled = false
            NewPswContainer.isErrorEnabled = false
            CConfirmPswContainer.isErrorEnabled = false

            if (xEmail.isNotEmpty() && xPassword.isNotEmpty() && yPassword.isNotEmpty() && zPassword.isNotEmpty()) {

                if (yPassword == zPassword) {
                    if(xPassword == yPassword) {
                        NewPswContainer.setError("Use a different Password")
                        edtCOldPsw.setText("")
                        edtCNewPsw.setText("")
                        edtCConfirmPsw.setText("")

                    }else
                        user!!.updatePassword(yPassword)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(this, "Successfully Changed", Toast.LENGTH_SHORT).show()
                                    val xx = Intent(this, LogIn::class.java)
                                    startActivity(xx)
                                } else {
                                    if (!Patterns.EMAIL_ADDRESS.matcher(edtCEmail.getText().toString())
                                            .matches()
                                    ) else {
                                        OldPswContainer.setError("Wrong Password")
                                        edtCOldPsw.setText("")
                                        edtCNewPsw.setText("")
                                        edtCConfirmPsw.setText("")

                                    }
                                }
                            }
                } else {
                    CConfirmPswContainer.setError("Password is not matching")
                    edtCOldPsw.setText("")
                    edtCNewPsw.setText("")
                    edtCConfirmPsw.setText("")
                }
            } else {
                Toast.makeText(this, "Please Fill in All the Field", Toast.LENGTH_SHORT).show()
                edtCOldPsw.setText("")
                edtCNewPsw.setText("")
                edtCConfirmPsw.setText("")

            }
            }
        }
    }
}
