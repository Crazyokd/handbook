package com.example.handbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DataGraphActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_graph)
        supportActionBar?.hide()
        val title:TextView=findViewById(R.id.bar_title_tv)
        title.text="数据图表"
    }
}