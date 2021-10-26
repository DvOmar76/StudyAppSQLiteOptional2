package com.example.studyapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AndroidActivity : AppCompatActivity() {
    var firstTimeOpenAndroid=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android)
        val dbHelper=DBHelper(applicationContext)
        val items = arrayListOf(
            arrayListOf("Project Setup", "Setting up an Android Studio Project.", "Detailed notes here."),
            arrayListOf("Console", "Printing to the console.", "Detailed notes here."),
            arrayListOf("Resource Files", "Identifying, editing, and using resource files.", "Detailed notes here."),
            arrayListOf("UI Elements", "Creating, modifying, and initializing UI Elements.", "Detailed notes here.")
        )
        loadData()
        // to enter this info one time
        if (!firstTimeOpenAndroid){
            for (i in 0..3){
                val title=items[i][0]
                val description=items[i][1]
                val details=items[i][2]
                dbHelper.add("Android",title,description,details)
            }
            firstTimeOpenAndroid=true
            saveData()
        }


        val list=dbHelper.getData("Android")
        val rvAndroid = findViewById<RecyclerView>(R.id.rvAndroid)
        rvAndroid.adapter = RVAdapter(this,list )
        rvAndroid.layoutManager = LinearLayoutManager(this)
        title = "Android Review"
    }

    fun saveData(){
        val sharedPreferences = getSharedPreferences("firstTimeOpenAndroid", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putBoolean("firstTimeOpenAndroid",firstTimeOpenAndroid)
        }.apply()
    }



    fun loadData(){
        val sharedPreferences = getSharedPreferences("firstTimeOpenAndroid",Context.MODE_PRIVATE)
        firstTimeOpenAndroid=sharedPreferences.getBoolean("firstTimeOpenAndroid",false)

    }

}