package com.example.handbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import java.lang.Thread.sleep

class LauncherActivity : AppCompatActivity() {

    private inner class MyThread:Thread() {
        override fun run() {
            try {
                sleep(3000)
                Intent()
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            catch ( e:Exception){
                e.printStackTrace()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
        setContentView(R.layout.activity_launcher)
      val myThread=MyThread()
        myThread.start()
    }
}