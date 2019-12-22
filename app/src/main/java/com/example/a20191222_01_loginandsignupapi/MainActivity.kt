package com.example.a20191222_01_loginandsignupapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValue()
    }

    override fun setupEvents() {

    }

    override fun setValue() {
        val userName = intent.getStringExtra("name")
        val userId = intent.getStringExtra("id")
        val userPhone = intent.getStringExtra("phone")

        userNameAndIdTxt.text = "${userName}(${userId})"
        phoneTxt.text = userPhone
    }

}
