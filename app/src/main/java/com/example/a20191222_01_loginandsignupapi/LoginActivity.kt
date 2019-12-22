package com.example.a20191222_01_loginandsignupapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a20191222_01_loginandsignupapi.datas.User
import com.example.a20191222_01_loginandsignupapi.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValue()
    }
    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            val intent = Intent(mContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()

            ConnectServer.postRequestLogin(mContext,inputId,inputPw, object :ConnectServer.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {
                    Log.d("로그인",json.toString())

                    val code = json.getInt("code")
                    if(code == 200){

                        val data= json.getJSONObject("data")
                        val user = data.getJSONObject("user")

//                        val userName = user.getString("name")
//                        val userId =  user.getString("login_id")
//                        val userPhone = user.getString("phone")
                        val userData = User.getUserDataFromJson(user)

//                        val userData = User(userId,userName,userPhone)


                        val intent = Intent(mContext, MainActivity::class.java)
                        intent.putExtra("name",userData.name)
                        intent.putExtra("id",userData.loginId)
                        intent.putExtra("phone",userData.phoneNum)
                        startActivity(intent)

                    }
                    else{
                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext,"${message}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

        }

    }

    override fun setValue() {
    }

}
