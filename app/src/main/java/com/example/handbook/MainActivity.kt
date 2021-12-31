package com.example.handbook

import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.handbook.Bill.Companion.TYPE_INCOME
import java.io.*

class MainActivity : AppCompatActivity() ,View.OnClickListener {

    private val income_laber_list = ArrayList<String>()
    private val expense_laber_list = ArrayList<String>()
    //private var bl = ArrayList<Bill>()
    private var income_month: Double = 0.0
    private var expense_month: Double = 0.0
    private var balance_month:Double= 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initExpenseLaberList()
        initIncomeLaberList()
        readData()

        val incomeAdd: ImageView = findViewById(R.id.income_add_iv)
        incomeAdd.setOnClickListener(this)
        val expenseAdd: ImageView = findViewById(R.id.expense_add_iv)
        expenseAdd.setOnClickListener(this)
        val datalist: ImageView = findViewById(R.id.datalist_iv)
        datalist.setOnClickListener(this)
        val datagraph: ImageView = findViewById(R.id.datagraph_iv)
        datagraph.setOnClickListener(this)

        Log.d("MainActivity", "onCreate")
        refreshRecyclerView()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.datalist_iv -> {
                val intent = Intent(this, DataListActivity::class.java)
                startActivityForResult(intent,1)
            }
            R.id.datagraph_iv -> {
                val intent = Intent(this, DataGraphActivity::class.java)
                startActivityForResult(intent,2)
            }
            R.id.income_add_iv -> {
                val intent = Intent(this, IncomeAddActivity::class.java)
                startActivityForResult(intent, 3)
            }
            R.id.expense_add_iv -> {
                val intent = Intent(this, ExpenseAddActivity::class.java)
                startActivityForResult(intent, 4)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if(resultCode== RESULT_OK){
                reCount()
                refreshRecyclerView()
            }
            3 -> if (resultCode == RESULT_OK) {
                val income_money: Double? = data?.getStringExtra("return_data_money")?.toDouble()
                val income_laber = income_laber_list[Integer.parseInt(data?.getStringExtra("return_data_laber"))]
                val income_comment = data?.getStringExtra("return_data_comment")
                val income_calendar = data?.getStringExtra("return_data_calendar")
                if (income_money != null) {
                    income_month += income_money
                    balance_month += income_money
                }
                BillList.bl.add(Bill(income_money, income_laber, income_comment, income_calendar, 1))
                Log.d("IncomeAdd", "${BillList.bl.size}")
                refreshRecyclerView()
            }

            4 -> if (resultCode == RESULT_OK) {
                Log.d("ExpenseButton", "$")
                val expense_money = data?.getStringExtra("return_data_money")?.toDouble()
                val expense_laber = expense_laber_list[Integer.parseInt(data?.getStringExtra("return_data_laber"))]
                val expense_comment = data?.getStringExtra("return_data_comment")
                val expense_calendar = data?.getStringExtra("return_data_calendar")

                if (expense_money != null) {
                    expense_month += expense_money
                    balance_month -= expense_money
                }
                BillList.bl.add(Bill(expense_money, expense_laber, expense_comment, expense_calendar, 0))
                refreshRecyclerView()
            }
            6->if(resultCode== RESULT_OK){
                reCount()
                Log.d("return_succeed","return_succeed")
                refreshRecyclerView()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SaveData","Succeed")
        saveData()
    }

    //override fun onStart() {
    //    super.onStart()
    //    Log.d("Save","Succeed")
    //    refreshRecyclerView()
    //}

    override fun onRestart() {
        super.onRestart()
        reCount()
        refreshRecyclerView()
    }
   fun reCount(){
       income_month=0.0
       expense_month=0.0
       for (bill in BillList.bl) {
           if(bill.money!=null){
               if (bill.ctgr == TYPE_INCOME)
                   income_month += bill.money
               else
                   expense_month+=bill.money
           }
       }
       balance_month=1000.0+income_month-expense_month
       Log.d("income_month","$income_month")
   }
    fun refreshRecyclerView() {
        val balance: TextView = findViewById(R.id.balance_tv)
        val income: TextView = findViewById(R.id.income_tv)
        val expense: TextView = findViewById(R.id.expense_tv)
        income.text = "本月收入:${String.format("%.1f",income_month)}"
        expense.text = "本月支出:${String.format("%.1f",expense_month)}"
        balance.text = "${String.format("%.1f",balance_month)}"

        var i=1;
        val fbl=ArrayList<Bill>()
        val siz=BillList.bl.size
        while(siz-i>=0&&i<=5){
              fbl.add(BillList.bl[siz-i])
              i++
        }
        val billList_main: RecyclerView = findViewById(R.id.billList_main_rv)
        val adapter = BillAdapter(fbl)
        billList_main.layoutManager = LinearLayoutManager(this)
        billList_main.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    private fun saveData() {
        var oos: ObjectOutputStream? = null
        try {
            val output=openFileOutput("billlist",Context.MODE_PRIVATE)
            oos= ObjectOutputStream(output)

            oos.writeObject(BillList.bl)
            oos.flush()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (e: IOException) {
                    e.printStackTrace();
                }
            }
        }
    }

    private fun readData(){

        var ois:ObjectInputStream?=null
        try {
            val input = openFileInput("billlist")
            ois = ObjectInputStream(input)
            BillList.bl = ois.readObject() as ArrayList<Bill>
            reCount()
        }catch (e:IOException){
            e.printStackTrace()
        }
        finally {
            if(ois!=null){
                try {
                    ois.close()
                }
                catch (e:IOException){
                    e.printStackTrace()
                }
            }
        }
    }
    private fun initIncomeLaberList(){
        income_laber_list.add("其他")
        income_laber_list.add("工资薪水")
        income_laber_list.add("基金股票")
        income_laber_list.add("被动收入")
        income_laber_list.add("兼职打工")
    }
    private fun initExpenseLaberList(){
        expense_laber_list.add("其他")
        expense_laber_list.add("零食饮料")
        expense_laber_list.add("学校饮食")
        expense_laber_list.add("外出娱乐")
        expense_laber_list.add("学习用品")
    }
}