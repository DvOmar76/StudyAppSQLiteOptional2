package com.example.studyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*
import java.lang.Exception

class Edit : AppCompatActivity() {
    lateinit var dbHelper: DBHelper
     var table=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        dbHelper = DBHelper(applicationContext)

        btnAdd.setOnClickListener {
            checkButton()
            if (table.isNotEmpty())
            {
                var title = edTitleAdd.text.toString()
                var description = edDescriptionADD.text.toString()
                var details = edDetailsAdd.text.toString()

                if (title.isNotEmpty() && description.isNotEmpty() && details.isNotEmpty()) {
                    val status = dbHelper.add("$table", title, description, details)
                    if (status != -1L)
                    {
                        Toast.makeText(applicationContext, "data is added", Toast.LENGTH_SHORT).show()
                        edTitleAdd.text.clear()
                        edDescriptionADD.text.clear()
                        edDetailsAdd.text.clear()

                    }
                    else
                    {
                        Toast.makeText(applicationContext, "error db", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(applicationContext, "please enter text", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(applicationContext, "please select table", Toast.LENGTH_SHORT).show()

            }

        }
        btnDelete.setOnClickListener {
            try
            {
                val id = edIDDelete.text.toString().toInt()
                val status = dbHelper.delete("kotlin", id)
                Log.d("asdfasd", status.toString())
                if (status == 1)
                {
                    Toast.makeText(applicationContext, "item is deleted", Toast.LENGTH_SHORT).show()
                    edIDDelete.text.clear()
                }
                else
                {
                    Toast.makeText(applicationContext, "id not found", Toast.LENGTH_SHORT).show()
                }
            }
            catch (e: Exception)
            {
                Toast.makeText(applicationContext, "please enter number in id", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        btnUpdate.setOnClickListener {
            checkButton()

            if (table.isNotEmpty())
            {
                try {
                    val id = edIDUpdate.text.toString().toInt()
                    var title = edTitleUpdate.text.toString()
                    var description = edDescriptionUpdate.text.toString()
                    var details = edDetailsUpdate.text.toString()
                    if (title.isNotEmpty() && description.isNotEmpty() && details.isNotEmpty())
                    {
                        val status = dbHelper.update("$table", id, title, description, details)
                        if (status == 0)
                        {
                            Toast.makeText(applicationContext, "id not found ", Toast.LENGTH_SHORT)
                                .show()
                        }
                        else
                        {
                            Toast.makeText(
                                applicationContext,
                                "item is updated",
                                Toast.LENGTH_SHORT
                            ).show()
                            edIDUpdate.text.clear()
                            edTitleUpdate.text.clear()
                            edDescriptionUpdate.text.clear()
                            edDetailsUpdate.text.clear()
                        }
                    }
                    else
                    {
                        Toast.makeText(applicationContext, "complete fields", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                catch (e: Exception)
                {
                    Toast.makeText(
                        applicationContext,
                        "please enter number in id",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
            else
            {
                Toast.makeText(applicationContext, "please select table", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun checkButton(){
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            var rb:RadioButton=findViewById(checkedId)
            if (rb!=null)
            {
                table=rb.text.toString()
            }
        }
    }

}