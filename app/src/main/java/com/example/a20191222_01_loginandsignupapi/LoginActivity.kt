package com.example.a20191222_01_loginandsignupapi

import android.os.Bundle

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValue()
    }
    override fun setupEvents() {

    }

    override fun setValue() {
    }

}