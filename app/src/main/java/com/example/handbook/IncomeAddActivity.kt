package com.example.handbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.*
import kotlin.concurrent.thread

class IncomeAddActivity : AppCompatActivity(), View.OnClickListener {

    // val arr=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_add)
        supportActionBar?.hide()

        val title: TextView = findViewById(R.id.bar_title_tv)
        title.text = "收入"

        val store: TextView = findViewById(R.id.store_bt)
        store.setOnClickListener(this)
        Log.d("IncomeAdd", "SucceedOnCreate")
        /*
        val spinner: Spinner = findViewById(R.id.income_spinner)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("Spinner", "$id")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        */
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.store_bt -> {
                val money: EditText = findViewById(R.id.income_money_et)
                val comment: EditText = findViewById(R.id.income_comment_et)
                val inputmoney = money.text.toString()
                if (inputmoney == "") {
                    Toast.makeText(this, "亲，您忘记填写收入金额了哦！", Toast.LENGTH_SHORT).show()
                    Log.d("StoreButton", "亲，您忘记填写金额了哦！")
                } else if (!BillList.isNumeric(inputmoney) || inputmoney.toDouble() <= 0) {
                    Toast.makeText(this, "亲，您填写的收入金额有误哦！", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent()
                    intent.putExtra("return_data_money", inputmoney)
                    val spinner: Spinner = findViewById(R.id.income_spinner)
                    val id = spinner.selectedItemId
                    intent.putExtra("return_data_label", "$id")
                    if (comment.text.toString() == "")
                        intent.putExtra("return_data_comment", "哎呀！还没有备注呢！")
                    else
                        intent.putExtra("return_data_comment", comment.text.toString())

                    val calendar = Calendar.getInstance().get(Calendar.YEAR).toString() + "." +
                            (Calendar.getInstance().get(Calendar.MONTH) + 1).toString() + "." +
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()
                    Log.d("StoreButton", "$calendar")
                    intent.putExtra("return_data_calendar", calendar)

                    setResult(RESULT_OK, intent)
                    Log.d("IncomeButton1", inputmoney)

                    finish()
                }
            }

        }
    }
}