package com.example.planetory


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class Pomodoro : AppCompatActivity() {

    private var timeCountDown: CountDownTimer? = null
    private var timeSelected: Int = 0
    private var timeProgress = 0
    private var pauseOffSet: Long = 0
    private var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pomodoro)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Pomodoro"

        val set_time: ImageButton = findViewById(R.id.btnSettime)
        set_time.setOnClickListener {
            setTime()
        }
        val btnStart: Button = findViewById(R.id.startButton)
        btnStart.setOnClickListener {
            startTimerSetup()
        }
        val btnRestart: ImageButton = findViewById(R.id.btnRestart)
        btnRestart.setOnClickListener {
            resetTime()
        }
    }

    private fun resetTime() {
        if (timeCountDown != null) {
            timeCountDown!!.cancel()
            timeProgress = 0
            timeSelected = 0
            pauseOffSet = 0
            timeCountDown = null
            val btnStart: Button = findViewById(R.id.startButton)
            btnStart.text = "Start"
            isStart = true
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.progress = 0
            val timeLeft: TextView = findViewById(R.id.txtTime)
            timeLeft.text = "0"

        }
    }

    private fun timePause() {
        if (timeCountDown != null) {
            timeCountDown!!.cancel()
        }
    }

    private fun startTimerSetup() {
        val btnStart: Button = findViewById(R.id.startButton)
        if (timeSelected > timeProgress) {
            if (isStart) {
                btnStart.text = "Pause"
                startTimer(pauseOffSet)
                isStart = false
            } else {
                isStart = true
                btnStart.text = "Resume"
                timePause()
            }
        }else{
            Toast.makeText(this,"Enter Time",Toast.LENGTH_SHORT).show()
        }
    }

    private fun startTimer(pauseOffSetL: Long) {

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.progress = timeProgress
        timeCountDown = object : CountDownTimer(
            (timeSelected * 1000).toLong() - pauseOffSetL * 1000, 1000)
        {
            override fun onTick(p0: Long) {
                timeProgress++
                pauseOffSet = timeSelected.toLong() - p0 / 1000
                progressBar.progress = timeSelected - timeProgress
                val timeLeft: TextView = findViewById(R.id.txtTime)
                timeLeft.text = (timeSelected - timeProgress).toString()
            }

            override fun onFinish() {
                resetTime()
                Toast.makeText(this@Pomodoro, "Times Up!", Toast.LENGTH_SHORT).show()
            }

        }.start()

    }

    private fun setTime() {

        val timeDialog = Dialog(this)
        timeDialog.setContentView(R.layout.dialog_timer)
        val timeSet = timeDialog.findViewById<EditText>(R.id.edtTimer)
        val timeLeft: TextView = findViewById(R.id.txtTime)
        val btnStart: Button = findViewById(R.id.startButton)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        timeDialog.findViewById<Button>(R.id.btnOK).setOnClickListener {
            if (timeSet.text.isEmpty()) {
                Toast.makeText(this, "Enter Time Duration", Toast.LENGTH_LONG).show()
            } else {
                resetTime()
                timeLeft.text = timeSet.text
                btnStart.text = "Start"
                timeSelected = timeSet.text.toString().toInt()
                progressBar.max = timeSelected
            }
            timeDialog.dismiss()
        }
        timeDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timeCountDown!= null){
            timeCountDown?.cancel()
            timeProgress = 0
        }
    }
}