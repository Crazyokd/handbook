package com.example.handbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataListActivity : AppCompatActivity() {
    public var flag:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_list)
        supportActionBar?.hide()
        val title: TextView =findViewById(R.id.bar_title_tv)
        title.text="数据列表"

        //val bl=this.intent.getSerializableExtra("bill_list") as ArrayList<Bill>
        refreshRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            6->if(resultCode== RESULT_OK){
                flag=true
                Log.d("Dreturn_succeed","ddd")
                refreshRecyclerView()
            }
        }
    }
    fun refreshRecyclerView() {
        val billList:RecyclerView=findViewById(R.id.billList_rv)
        var i=1;
        val fbl=ArrayList<Bill>()
        val siz=BillList.bl.size
        while(siz-i>=0){
            fbl.add(BillList.bl[siz-i])
            i++
        }
        val adapter=BillAdapter(fbl)
        billList.layoutManager=LinearLayoutManager(this)
        billList.adapter=adapter
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        intent.putExtra("flag",flag)
        setResult(RESULT_OK,intent)
        finish()
    }

}