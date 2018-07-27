package com.example.nikhil.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class Times_Up : AppCompatActivity() {
    lateinit var mp:MediaPlayer

    var clicked=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_times__up)

        mp=MediaPlayer.create(this,R.raw.fire_engine)
        mp.start()

        var info_layout=findViewById<LinearLayout>(R.id.info_layout)
        var info_btn=findViewById<Button>(R.id.info_btn)
        var touch_button=findViewById<Button>(R.id.touch_button)
        var times_up_tv=findViewById<TextView>(R.id.times_up_tv)

        var counter=info_layout
        info_layout.visibility=View.INVISIBLE

        counter.animate().translationYBy(1000F)

        info_btn.setOnClickListener(View.OnClickListener {

            if (clicked==0){
                info_layout.visibility=View.VISIBLE
                times_up_tv.animate().translationXBy(-1000F)
                counter.animate().translationYBy(-1000F)
                clicked=1


            }
            else{
                times_up_tv.animate().translationXBy(1000F)
                counter.animate().translationYBy(1000F)
                info_layout.animate().setDuration(500)
                info_layout.visibility=View.INVISIBLE
                clicked=0

            }

        })
        touch_button.setOnClickListener(View.OnClickListener {

            var i= Intent(this@Times_Up,MainActivity::class.java)
            startActivity(i)
            mp.pause()



        })



    }
}
