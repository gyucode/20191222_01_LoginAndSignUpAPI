package com.example.a20191222_01_loginandsignupapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a20191222_01_loginandsignupapi.datas.User
import com.example.a20191222_01_loginandsignupapi.utils.ConnectServer
import com.example.a20191222_01_loginandsignupapi.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_edit_black_list.*
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class EditBlackListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_black_list)
        setupEvents()
        setValue()
    }

    override fun setupEvents() {
        blackRegBtn.setOnClickListener {

            val inputTitle = titleEdt.text.toString()
            val inputPhone = phoneEdt.text.toString()
            val inputContent = contentEdt.text.toString()

            ConnectServer.postRequestBlackList(mContext, ContextUtil.getUserToken(mContext),inputTitle,inputPhone,inputContent,
                object : ConnectServer.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {
                        Log.d("게시글 등록",json.toString())
                        val code = json.getInt("code")
                        if(code == 200){
                            runOnUiThread {
                                Toast.makeText(mContext, "게시글이 등록 되었습니다", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                        else {
                            val message = json.getString("message")
                            runOnUiThread {
                                Toast.makeText(mContext, "${message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                })
            }

    }

    override fun setValue() {

    }

}

