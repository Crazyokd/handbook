package com.example.handbook

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

import kotlin.concurrent.thread


class LoginActivity : AppCompatActivity(), View.OnClickListener {

    //    private val username : EditText = findViewById(R.id.userEditText)
//    private val password : EditText = findViewById(R.id.pwdEditText)
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        username = findViewById(R.id.userEditText)
        password = findViewById(R.id.pwdEditText)

        //注册三个按钮
        val loginBtn: Button = findViewById(R.id.bnLogin)
        loginBtn.setOnClickListener(this)
        val cancelBtn: Button = findViewById(R.id.bnCancel)
        cancelBtn.setOnClickListener(this)
        val registerTv: TextView = findViewById(R.id.register_tv)
        registerTv.setOnClickListener(this)

        Log.d("LoginActivity", "onCreate")

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bnLogin -> {
                loginUser()
            }
            R.id.register_tv -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.bnCancel -> {
                username.setText("")
                password.setText("")
            }
        }
    }


    private fun showErrorMessage() {
        runOnUiThread {
            val testTv: TextView = findViewById(R.id.test_tv)
            testTv.text = "您的账号或密码错误"
        }
    }


    private fun loginUser() {
        //开启线程发起网络请求
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.0.2.2:8080/handbook/login-servlet?username=${username.text}&password=${password.text}")
                    .build()
                val response = client.newCall(request).execute()
                val responseData = response.body()?.string()
                // 对返回的数据进行判断处理
                if (responseData.equals("success")) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("username", username.text)
                    startActivityForResult(intent, 2)
                } else {
                    showErrorMessage()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}