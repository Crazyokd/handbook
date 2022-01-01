package com.example.handbook

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.concurrent.thread

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bnRegister -> {
                Log.d("test", "register clicked")
                registerUser()
            }
            R.id.bnCancel -> {
                userEditText.setText("")
                pwdEditText.setText("")
                cfPwdEditText.setText("")
            }
        }
    }

    private lateinit var userEditText: EditText
    private lateinit var pwdEditText: EditText
    private lateinit var cfPwdEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        userEditText = findViewById(R.id.userEditText)
        pwdEditText = findViewById(R.id.pwdEditText)
        cfPwdEditText = findViewById(R.id.cf_pwdEditText)

        findViewById<Button>(R.id.bnRegister).setOnClickListener(this)
        findViewById<Button>(R.id.bnCancel).setOnClickListener(this)
    }

    private fun registerUser() {
        val username = userEditText.text.toString()
        val password = pwdEditText.text.toString()
        val cfPassword = cfPwdEditText.text.toString()
        Log.d("test", "username = $username, password = $password, cfPassword = $cfPassword")

        if (password != cfPassword) {
            Toast.makeText(this, "您两次输入的密码不一致", Toast.LENGTH_SHORT).show()
        } else {
            thread {
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder()
                        .url("http://10.0.2.2:8080/handbook/register-servlet?username=${username}&password=${password}")
                        .build()
                    val response = client.newCall(request).execute()
                    val responseData = response.body()?.string()
                    // 对返回的数据进行判断处理
                    if (responseData.equals("success")) {
                        showMessage("注册成功")
                    } else {
                        showMessage("用户名已存在或网络异常")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun showMessage(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}