package com.example.nikhil.myapplication

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var tv_show :TextView
    lateinit var sk : SeekBar
    lateinit var  go_btn:Button
    var Timer_state=0   // 0 = timer not running    1 = Timer running
    lateinit var CountDownTimer:CountDownTimer
    lateinit var loading_iv:ImageView
    lateinit var loading_iv_2:ImageView


    fun updateTimer(secondsLeft : Int){             //fn to update Timer

           var minutes = secondsLeft / 60
           var seconds = secondsLeft - minutes * 60
        if(seconds<=9)
        {
           tv_show.text = "${minutes}:0${seconds}"
        }
        else
           tv_show.text = "${minutes}:${seconds}"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         tv_show = findViewById<TextView>(R.id.tv_show)
         go_btn=findViewById<Button>(R.id.go_btn)
         sk=findViewById<SeekBar>(R.id.sk)
        loading_iv=findViewById(R.id.loading_iv)
        loading_iv_2=findViewById(R.id.loading_iv_2)

        class media_sound_stop(){


        }



        sk.max=1200
        sk.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateTimer(progress)


            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        go_btn.setOnClickListener(View.OnClickListener {

            if (sk.progress == 0) {

                Toast.makeText(this, "Please Set The Timer First", Toast.LENGTH_SHORT).show()
                go_btn.isActivated=false

            }
            else {

                if (go_btn.text == "GO") {

                    sk.isEnabled=false
                    CountDownTimer= object : CountDownTimer(sk.progress * 1000.toLong(), 1000) {

                        override fun onFinish() {
                            sk.progress = 0
                            Timer_state = 0
                            sk.isEnabled=true
                            go_btn.text = "GO"
                            var i=Intent(this@MainActivity,Times_Up::class.java)
                            startActivity(i)




                        }

                        override fun onTick(millisUntilFinished: Long) {
                            if (Timer_state == 0) {
                                go_btn.text = "STOP"
                                Timer_state = 1

                            }
                            loading_iv.animate().rotation((millisUntilFinished / 1000)*6.toFloat())            ////multiply second with 6 degree to rotate second's hand 6 degree every second
                            loading_iv_2.animate().rotation(((millisUntilFinished / 1000)/60)*6.toFloat())    //divide seconds by 60 to change in minutes and then rotate 6 degree to rotate minute's hand 6 degree every minute
                            updateTimer((millisUntilFinished / 1000).toInt())


                        }
                    }.start()

                }    //else code runs when we abort timer
                else if(go_btn.text=="STOP"){
                    loading_iv.animate().rotation(0F)
                    loading_iv_2.animate().rotation(0F)
                    tv_show.text="0:00"
                    sk.progress=0
                    CountDownTimer.cancel()
                    sk.isEnabled=true
                    go_btn.text="GO"
                    Timer_state = 0   // set timer state to zero




                }

            }
        })








    }


}

