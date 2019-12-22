package com.example.a20191222_01_loginandsignupapi

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

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

    }

    override fun setValue() {
    }

}
