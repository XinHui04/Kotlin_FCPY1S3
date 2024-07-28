package com.example.planetory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText

class ToDoList : AppCompatActivity() {

    lateinit var checkBox1 : CheckBox
    lateinit var checkBox2 : CheckBox
    lateinit var checkBox3 : CheckBox
    lateinit var checkBox4 : CheckBox
    lateinit var checkBox5 : CheckBox
    lateinit var checkBox6 : CheckBox
    lateinit var checkBox7 : CheckBox
    lateinit var checkBox8 : CheckBox
    lateinit var checkBox9 : CheckBox

    lateinit var task1 : EditText
    lateinit var task2 : EditText
    lateinit var task3 : EditText
    lateinit var task4 : EditText
    lateinit var task5 : EditText
    lateinit var task6 : EditText
    lateinit var task7 : EditText
    lateinit var task8 : EditText
    lateinit var task9 : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "To Do List"

        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        checkBox5 = findViewById(R.id.checkBox5)
        checkBox6 = findViewById(R.id.checkBox6)
        checkBox7 = findViewById(R.id.checkBox7)
        checkBox8 = findViewById(R.id.checkBox8)
        checkBox9 = findViewById(R.id.checkBox9)

        task1 = findViewById(R.id.task1)
        task2 = findViewById(R.id.task2)
        task3 = findViewById(R.id.task3)
        task4 = findViewById(R.id.task4)
        task5 = findViewById(R.id.task5)
        task6 = findViewById(R.id.task6)
        task7 = findViewById(R.id.task7)
        task8 = findViewById(R.id.task8)
        task9 = findViewById(R.id.task9)

        checkBox1.setOnClickListener{
            if (checkBox1.isChecked){
                task1.isEnabled = false
            }else{
                task1.isEnabled = true
            }
        }
        checkBox2.setOnClickListener{
            if (checkBox2.isChecked){
                task2.isEnabled = false
            }else{
                task2.isEnabled = true
            }
        }
        checkBox3.setOnClickListener{
            if (checkBox3.isChecked){
                task3.isEnabled = false
            }else{
                task3.isEnabled = true
            }
        }
        checkBox4.setOnClickListener{
            if (checkBox4.isChecked){
                task4.isEnabled = false
            }else{
                task4.isEnabled = true
            }
        }
        checkBox5.setOnClickListener{
            if (checkBox5.isChecked){
                task5.isEnabled = false
            }else{
                task5.isEnabled = true
            }
        }
        checkBox6.setOnClickListener{
            if (checkBox6.isChecked){
                task6.isEnabled = false
            }else{
                task6.isEnabled = true
            }
        }
        checkBox7.setOnClickListener{
            if (checkBox7.isChecked){
                task7.isEnabled = false
            }else{
                task7.isEnabled = true
            }
        }
        checkBox8.setOnClickListener{
            if (checkBox8.isChecked){
                task8.isEnabled = false
            }else{
                task8.isEnabled = true
            }
        }
        checkBox9.setOnClickListener{
            if (checkBox9.isChecked){
                task9.isEnabled = false
            }else{
                task9.isEnabled = true
            }
        }
    }
}