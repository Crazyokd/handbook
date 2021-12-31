package com.example.handbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import java.util.*

class ExpenseAddActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_add)
        supportActionBar?.hide()
        val title: TextView =findViewById(R.id.bar_title_tv)
        title.text="支出"

        val store: TextView = findViewById(R.id.store_copy_bt)
        store.setOnClickListener(this)
    }
    override fun onClick(v:View?){
        when(v?.id) {
            R.id.store_copy_bt -> {
                val money: EditText = findViewById(R.id.expense_money_et)
                val comment: EditText = findViewById(R.id.expense_comment_et)
                val inputmoney = money.text.toString()
                if (inputmoney == "") {
                    Toast.makeText(this, "亲，您忘记填写支出金额了哦！", Toast.LENGTH_SHORT).show()
                } else if(!BillList.isNumeric(inputmoney)||inputmoney.toDouble()<=0){
                    Toast.makeText(this, "亲，您填写的支出金额有误哦！", Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent()
                    intent.putExtra("return_data_money", inputmoney)
                    val spinner: Spinner = findViewById(R.id.expense_spinner)
                    val id = spinner.selectedItemId
                    intent.putExtra("return_data_laber", "$id")
                    if (comment.text.toString() == "")
                        intent.putExtra("return_data_comment", "哎呀！还没有备注呢！")
                    else
                        intent.putExtra("return_data_comment", comment.text.toString())

                    val calendar = Calendar.getInstance().get(Calendar.YEAR).toString() + "." +
                            (Calendar.getInstance().get(Calendar.MONTH) + 1).toString() + "." +
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()

                    intent.putExtra("return_data_calendar", calendar)

                    setResult(RESULT_OK, intent)
                    Log.d("ExpenseButton1", inputmoney)
                    finish()
                }
            }

        }
    }
}