package com.example.handbook

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.*

class StoreLayout(context: Context,attrs:AttributeSet):LinearLayout(context,attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.store_layout, this)
        val store: TextView = findViewById(R.id.store_tv)
        //store?.setOnClickListener {
            //保存后把数据传递至Bill数组
          //  val activity = context as Activity

      //  }
    }
}