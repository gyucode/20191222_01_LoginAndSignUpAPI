package com.example.a20191222_01_loginandsignupapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a20191222_01_loginandsignupapi.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValue()
    }

    override fun setupEvents() {
        signUpBtn.setOnClickListener {
            val inputId = idEdt.text.toString()
            val inputPw = pwEdt.text.toString()
            val inputName = nameEdt.text.toString()
            val inputPhone = phonEdt.text.toString()

            ConnectServer.putRequestSignUp(
                mContext,
                inputId,
                inputPw,
                inputName,
                inputPhone,
                object : ConnectServer.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        Log.d("회원가입응답",json.toString())

//                        서버가 내려주는 code값이 몇인지? 확인.
                        val code = json.getInt("code")
                        val msg = json.getString("message")
                        Log.d("회원가입코드","${code}")

                        runOnUiThread {
//                            연습문제: 토스트의 내용을 직접 코딩이 아니라 서버에서 내려주는 message를 받아서.
                            Toast.makeText(mContext,"${msg}",Toast.LENGTH_SHORT).show()

                            if(code == 200){
                                finish()
                            }
                        }



                    }

                })

        }

    }

    override fun setValue() {
    }

}
