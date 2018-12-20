package startup.softflix.com.startup

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.media.projection.MediaProjection
import android.text.Selection
import android.widget.Toast

/**
 * Created by Zanis on 12/20/2018.
 */

class DbManager{

    //name of database
    val dbName="MyNotes"
    //database table
    val dbTable="Notes"
    //properties of table
    val colID="ID"
    val colTitle="Title"
    val colDes="Description"
    //version of database, it is important so that when update we can track
    val dbVersion=1
    //CREATE TABLE IF NOT EXISTS MyNotes (ID INTEGER PRIMARY KEY,title TEXT, Description TEXT);" because we don't want to create table everytime
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+ dbTable +" ("+ colID +" INTEGER PRIMARY KEY,"+
            colTitle + " TEXT, "+ colDes +" TEXT);"

    //instance of sqllite database
    var sqlDB:SQLiteDatabase?=null


    constructor(context: Context)
    {
        var db=DatabaseHelperNotes(context)
        //write the database
        sqlDB=db.writableDatabase

    }

     //database helper, create database and execute commands, with inner class it will be able to access something i will do
    inner class DatabaseHelperNotes:SQLiteOpenHelper{

         var context:Context?=null
         //CONTEXT means this activity in which we are working
         constructor(context:Context    ):super(context, dbName, null, dbVersion){
             this.context=context
         }
         //after creating database this function will work
         override fun onCreate(p0: SQLiteDatabase?) {
             p0!!.execSQL(sqlCreateTable)
             Toast.makeText(this.context,"database is created", Toast.LENGTH_LONG).show()


         }

         override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
             //drop the table if exists
             p0!!.execSQL("Drop table IF EXISTS" + dbTable )

         }

     }


    //method to insert in database
    //content value is like getting value as key value, return type is Long
    fun Insert(values:ContentValues):Long{
        val ID= sqlDB!!.insert(dbTable, "",values)
        return ID//if id is 0 means i don't know otherwise means Ok
    }

    //projection means which colums you want to get, selection means which rows to get, 3rd parameter means sort or not
    fun Query(projection:Array<String>, selection:String,selectionArgs: Array<String>, sortOrder:String ):Cursor//output is cursor(just like table)
    {
        val  qb= SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor=qb.query(sqlDB,projection,selection,selectionArgs,null,null,sortOrder)
        return cursor
    }
 }