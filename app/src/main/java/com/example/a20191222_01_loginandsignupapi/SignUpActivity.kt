package com.example.a20191222_01_loginandsignupapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

                    }

                })

        }

    }

    override fun setValue() {
    }

}
