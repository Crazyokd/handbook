package com.example.handbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.handbook.Bill.Companion.TYPE_INCOME

class BillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        supportActionBar?.hide()
        val title: TextView =findViewById(R.id.bar_title_tv)
        title.text="账单"

        //val billlist=this.intent.getSerializableExtra("billlist") as ArrayList<Bill>
        val position=BillList.bl.size-this.intent.getIntExtra("position",0)-1
        Log.d("BillActivity","$position")
        //val bill=billlist[position]
        val methon:TextView=findViewById(R.id.exchange_method_tv)
        if(BillList.bl[position].ctgr==TYPE_INCOME) methon.text="收入"
        else methon.text="支出"
        val money:TextView=findViewById(R.id.exchange_money_tv)
        money.text=BillList.bl[position].money.toString()
        val ctgr:TextView=findViewById(R.id.exchange_ctgr_tv)
        ctgr.text=BillList.bl[position].label
        val time:TextView=findViewById(R.id.exchange_time_tv)
        time.text=BillList.bl[position].calendar
        val comment:TextView=findViewById(R.id.exchange_comment_et)
        comment.text=BillList.bl[position].comment
        val store:Button=findViewById(R.id.store_bill_bt)
        store.setOnClickListener{

               AlertDialog.Builder(this).apply {
                   setTitle("您是否想要保存并退出")
                   setMessage("每一笔钱都有它的价值")
                   setIcon(R.drawable.ic_income_add_foreground)
                   setCancelable(false)
                   setPositiveButton("确定"){dialog,which->
                       if(BillList.bl[position].comment!=comment.text.toString()) {
                           setResult(RESULT_OK, intent)
                           BillList.bl[position].comment = comment.text.toString()
                       }
                       finish()
                   }
                   setNegativeButton("取消"){dialog,which->
                   }
               }.show()

        }
        val delete:Button=findViewById(R.id.delete_bt)
        delete.setOnClickListener{
            AlertDialog.Builder(this).apply {
                setTitle("您是否想要删除")
                setMessage("删除后将无法再找到，请三思啊！")
                setIcon(R.drawable.ic_expense_add_foreground)
                setCancelable(false)
                setPositiveButton("确定"){dialog,which->
                    BillList.bl.removeAt(position)
                    setResult(RESULT_OK)
                    finish()
                }
                setNegativeButton("取消"){dialog,which->
                }
            }.show()
        }
    }
}