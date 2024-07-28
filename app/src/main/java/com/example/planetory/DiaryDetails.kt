package com.example.planetory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class DiaryDetails : AppCompatActivity() {
    lateinit var docRef: DocumentReference
    lateinit var edtDTitle: EditText
    lateinit var DTitleContainer: TextInputLayout
    lateinit var edtDContent: EditText
    lateinit var DContentContainer: TextInputLayout
    lateinit var btnDSave: ImageButton
    lateinit var txtDPgName: TextView
    lateinit var title: String
    lateinit var content: String
    lateinit var docId: String
    var isEditMode: Boolean = false
    lateinit var btnDDelete: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_details)

        edtDTitle = findViewById(R.id.edtDTitle)
        DTitleContainer = findViewById(R.id.DTitleContainer)
        edtDContent = findViewById(R.id.edtDContent)
        DContentContainer = findViewById(R.id.DContentContainer)
        btnDSave = findViewById(R.id.btnDSave)
        txtDPgName = findViewById(R.id.txtDPgName)
        btnDDelete = findViewById(R.id.btnDDelete)

        title = intent.getStringExtra("title") ?: ""
        content = intent.getStringExtra("content") ?: ""
        docId = intent.getStringExtra("docId") ?: ""

        if(docId!=null && !docId.isEmpty()){
            isEditMode = true
            btnDDelete.visibility = View.VISIBLE
        }

        edtDTitle.setText(title)
        edtDContent.setText(content)
        if(isEditMode){
            txtDPgName.setText("Edit your Diary")
        }

        btnDSave.setOnClickListener {
            saveDiary()
        }

        btnDDelete.setOnClickListener {
            deleteDiary()
        }
    }

    fun saveDiary(){
        var xTitle = edtDTitle.text.toString().trim()
        var yContent = edtDContent.text.toString().trim()

        if(isEditMode){
            docRef = FirebaseFirestore.getInstance().collection("diaries").document(docId)
        } else{
            docRef = FirebaseFirestore.getInstance().collection("diaries").document()
        }

        DTitleContainer.isErrorEnabled = false
        DContentContainer.isErrorEnabled = false

        if(xTitle.isNotEmpty() && yContent.isNotEmpty()) {

            val data = createDiaryData(xTitle,yContent)

            docRef.set(data).addOnCompleteListener { status ->
                if (status.isSuccessful) {
                    Toast.makeText(this, "Diary added successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed while adding diary", Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            if(xTitle.isEmpty() && yContent.isEmpty()) {
                DTitleContainer.setError("Title is required!")
                DContentContainer.setError("Content is required!")
                Toast.makeText(this, "Please Fill in All the Field", Toast.LENGTH_SHORT).show()
            } else {
                if(xTitle.isEmpty()) {
                    DTitleContainer.setError("Title is required!")
                } else {
                    if(yContent.isEmpty()) {
                        DContentContainer.setError("Content is required!")
                    }
                }
            }
        }
    }

    fun createDiaryData(title: String, content: String): HashMap<String, Any> {
        return hashMapOf(
            "title" to title,
            "content" to content,
            "timestamp" to Timestamp.now()
        )
    }

    fun deleteDiary(){
        docRef = FirebaseFirestore.getInstance().collection("diaries").document(docId)

        docRef.delete().addOnCompleteListener { status ->
            if (status.isSuccessful) {
                Toast.makeText(this, "Diary deleted successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed while deleting diary", Toast.LENGTH_SHORT).show()
            }
        }

    }
}