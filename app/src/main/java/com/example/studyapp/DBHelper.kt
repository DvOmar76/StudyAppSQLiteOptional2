package com.example.studyapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"StudyApp",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        if (db!=null){
            db.execSQL("create table Kotlin(id Integer primary key ,title text, description text ,details text)")
            db.execSQL("create table Android(id Integer primary key ,title text, description text ,details text)")
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun add(table:String,title:String,description:String,details:String): Long {
        val cv=ContentValues()
        cv.put("title",title)
        cv.put("description",description)
        cv.put("details",details)

       return this.writableDatabase.insert("$table",null,cv)
    }
    fun getData(table:String):ArrayList<Data>{
        val data=ArrayList<Data>()
        val c:Cursor=this.readableDatabase.query("$table",null,null,null,null,null,null)
        if(c.moveToFirst()){
            var id=c.getInt(c.getColumnIndex("id"))
            var title=c.getString(c.getColumnIndex("title"))
            var description=c.getString(c.getColumnIndex("description"))
            var details=c.getString(c.getColumnIndex("details"))
            data.add(Data(id,title,description,details))
            while (c.moveToNext()){
                 id=c.getInt(c.getColumnIndex("id"))
                 title=c.getString(c.getColumnIndex("title"))
                 description=c.getString(c.getColumnIndex("description"))
                 details=c.getString(c.getColumnIndex("details"))
                data.add(Data(id,title,description,details))
            }
        }
        c.close()
        return data
    }
    fun delete(table:String,id:Int): Int {
        return this.writableDatabase.delete("$table","id=?", arrayOf(id.toString()))
    }
    fun update(table:String,id: Int,title: String,description: String,details: String): Int {
        val cv=ContentValues()
        cv.put("title",title)
        cv.put("description",description)
        cv.put("details",details)
        return this.writableDatabase.update("$table",cv,"id=?", arrayOf(id.toString()))
    }
}