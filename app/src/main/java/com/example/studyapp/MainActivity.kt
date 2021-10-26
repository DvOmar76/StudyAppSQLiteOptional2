package com.example.studyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btAndroid = findViewById<Button>(R.id.btAndroid)
        btAndroid.setOnClickListener {
            val intent = Intent(this, AndroidActivity::class.java)
            startActivity(intent)
        }

        val btKotlin = findViewById<Button>(R.id.btKotlin)
        btKotlin.setOnClickListener {
            val intent = Intent(this, KotlinActivity::class.java)
            startActivity(intent)
        }
        btnEdit.setOnClickListener {
            startActivity(Intent(applicationContext,Edit::class.java))
        }
    }
}