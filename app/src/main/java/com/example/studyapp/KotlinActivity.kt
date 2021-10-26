package com.example.studyapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class KotlinActivity : AppCompatActivity() {
     var firstTimeOpenKotlin=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        val dbHelper=DBHelper(applicationContext)


        val items = arrayListOf(
            arrayListOf("var and val", "Declaring variables.", "Detailed notes here."),
            arrayListOf("User Input", "Getting user input.", "Detailed notes here."),
            arrayListOf("Strings", "String concatenations, interpolation, and methods.", "Detailed notes here."),
            arrayListOf("Data Types", "Understanding data types.", "Detailed notes here."),
            arrayListOf("Basic Operations", "Performing math operations in Kotlin.", "Detailed notes here."),
            arrayListOf("If Statements", "Understanding when and how to use if, else if, and else statements.", "Detailed notes here."),
            arrayListOf("Error Handling", "Using try blocks to avoid runtime errors.", "Detailed notes here."),
            arrayListOf("For Loops", "Using for loops to automate code.", "Detailed notes here."),
            arrayListOf("While Loops", "Using while loops to automate code.", "Detailed notes here.")
        )

        loadData()
        // to enter this info one time
        if (!firstTimeOpenKotlin){
            for (i in 0..8){
                val title=items[i][0]
                val description=items[i][1]
                val details=items[i][2]
                dbHelper.add("Kotlin",title,description,details)
            }
            firstTimeOpenKotlin=true
            saveData()
        }
       val list=dbHelper.getData("Kotlin")
        val rvKotlin = findViewById<RecyclerView>(R.id.rvKotlin)
        rvKotlin.adapter = RVAdapter(this, list)
        rvKotlin.layoutManager = LinearLayoutManager(this)

        title = "Kotlin Review"
    }

    fun saveData(){
        val sharedPreferences = getSharedPreferences("firstTimeOpenKotlin", Context.MODE_PRIVATE)
        val editor=sharedPreferences.edit()
        editor.apply{
            putBoolean("firstTimeOpenKotlin",firstTimeOpenKotlin)
        }.apply()
    }



    fun loadData(){
        val sharedPreferences = getSharedPreferences("firstTimeOpenKotlin", Context.MODE_PRIVATE)
        firstTimeOpenKotlin=sharedPreferences.getBoolean("firstTimeOpenKotlin",false)

    }
}