package com.translateall.language.free.translator.dictionary.speechtext.learnenglish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room

class MainNew : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val db = Room.databaseBuilder(this, MyDataClassDatabase::class.java, "aaa").allowMainThreadQueries().build()

        db.daousage().insertdatatodb(MyDataClass(name = "now"))

    }
}