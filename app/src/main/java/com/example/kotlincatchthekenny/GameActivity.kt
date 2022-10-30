package com.example.kotlincatchthekenny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    var score = 0
    var imageArray = mutableListOf<ImageView>()
    var runnable = Runnable {}
    var handler = Handler(Looper.getMainLooper())
    var level = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val intent = intent
        level = intent.getIntExtra("level",0)

        //ImageArray
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)

        hideImages()


        //CountDown Timer
        object : CountDownTimer(15500,1000) {
            override fun onTick(millis: Long) {
                timeView.text = "Time: ${millis/1000}"
            }

            override fun onFinish() {
                timeView.text = "Time: 0"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                var alert = AlertDialog.Builder(this@GameActivity)
                alert.setTitle("Your Score is $score")
                alert.setMessage("Do you want to restart the game?")

                alert.setPositiveButton("Yes") {dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alert.setNegativeButton("No") {dialog, which ->
                    Toast.makeText(this@GameActivity,"Game Over",Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                alert.show()
            }

        }.start()
    }

    fun increaseScore(view: View){
        score++
        scoreView.text = "Score: $score"
    }

    fun hideImages(){
        runnable = object : Runnable {
            override fun run() {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }
                val randomIndex = Random.Default.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(this, level.toLong())
            }

        }

        handler.post(runnable)

    }



}