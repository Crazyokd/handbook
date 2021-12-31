package com.example.handbook


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.handbook.Bill.Companion.TYPE_INCOME


open class BillAdapter(val billList:List<Bill>):
    RecyclerView.Adapter<BillAdapter.ViewHolder>() {

    /*inner class IncomeViewHolder(view:View):RecyclerView.ViewHolder(view){
        val billLaber:TextView=view.findViewById(R.id.laber_tv)
        val billComment:TextView=view.findViewById(R.id.comment_tv)
        val billMoney:TextView=view.findViewById(R.id.money_tv)

    }

    inner class ExpenseViewHolder(view:View):RecyclerView.ViewHolder(view){
        val billLaber:TextView=view.findViewById(R.id.laber_tv)
        val billComment:TextView=view.findViewById(R.id.comment_tv)
        val billMoney:TextView=view.findViewById(R.id.money_tv)

    }*/
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val billTime:TextView=view.findViewById(R.id.time_tv)
        val billLaber:TextView=view.findViewById(R.id.laber_tv)
        val billComment:TextView=view.findViewById(R.id.comment_tv)
        val billMoney:TextView=view.findViewById(R.id.money_tv)
        val billSymbol:ImageView=view.findViewById(R.id.symbol_iv)
    }

    override fun getItemViewType(position: Int) =billList[position].ctgr

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bill=billList[position]
        if(getItemViewType(position)== TYPE_INCOME){
            holder.billSymbol.setImageResource(R.drawable.ic_money_increase_foreground)
            holder.billMoney.text="￥"+bill.money.toString()
        }
        else{
            holder.billSymbol.setImageResource(R.drawable.ic_money_decrease_foreground)
            holder.billMoney.text="￥-"+bill.money.toString()
        }
        holder.billTime.text=bill.calendar
        holder.billLaber.text="标签："+bill.laber
        holder.billComment.text="备注："+bill.comment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.bill_item, parent, false)
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
    override fun getItemCount()=billList.size

}