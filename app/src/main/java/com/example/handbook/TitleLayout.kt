package com.example.handbook

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class TitleLayout(context: Context,attrs:AttributeSet):LinearLayout(context,attrs) {
    init{
        LayoutInflater.from(context).inflate(R.layout.bar_title_layout,this)
        val titleBack:ImageView=findViewById(R.id.bar_title_iv)
        titleBack.setOnClickListener{
            val activity=context as Activity
            //activity.onBackPressed()
            val intent=Intent()
            activity.setResult(AppCompatActivity.RESULT_CANCELED)
            Log.d("TitleBar","SucceedCancel")
            activity.finish()
        }
    }
}