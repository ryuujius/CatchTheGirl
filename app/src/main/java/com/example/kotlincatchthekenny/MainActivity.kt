package com.example.kotlincatchthekenny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {


    var level = 0
    val coefficant = 500


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun easy(view: View){
        gameIntent(level+coefficant)
    }
    fun normal(view: View){
        gameIntent(level+coefficant/2)
    }
    fun hard(view: View){
        gameIntent(level+coefficant/4)
    }

    fun gameIntent(integer : Int){
        val intent = Intent(applicationContext,GameActivity::class.java)
        intent.putExtra("level", integer)
        startActivity(intent)
        finish()
    }



}