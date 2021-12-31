package com.example.handbook

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

class MainBillAdapter(billList: List<Bill>):BillAdapter(billList) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.bill_item, parent, false)
        val viewHolder=ViewHolder(view)
        Log.d("Adapter","${viewHolder.billMoney}")
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val bill=billList[position]
            val intent= Intent("com.example.handbook.ACTION_BILL")
            val activity=parent.context as Activity
            //val bundle = Bundle()
            //bundle.putSerializable("billlist", billList)
            //intent.putExtras(bundle)
            intent.putExtra("position",position)
            activity.startActivityForResult(intent,6)

        }
        return viewHolder
    }
}