package com.example.a20191222_01_loginandsignupapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a20191222_01_loginandsignupapi.adapters.BlackListAdapter
import com.example.a20191222_01_loginandsignupapi.datas.BlackListData
import com.example.a20191222_01_loginandsignupapi.datas.User
import com.example.a20191222_01_loginandsignupapi.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val blackList = ArrayList<BlackListData>()
    var blackListDataAdapter:BlackListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValue()
    }

    override fun setupEvents() {
        postBlackListBtn.setOnClickListener {
            val intent = Intent(mContext,EditBlackListActivity::class.java)
            startActivity(intent)
        }

    }

    override fun setValue() {
        val user= intent.getSerializableExtra("user") as User

        userNameAndIdTxt.text = "${user.name}(${user.loginId})"
        phoneTxt.text = user.phoneNum

        getBlackListsFromServer()

        blackListDataAdapter = BlackListAdapter(mContext, R.layout.black_list_item, blackList)
        blackListview.adapter = blackListDataAdapter
    }

    fun getBlackListsFromServer(){
        ConnectServer.getRequestBlackList(mContext, object:ConnectServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("블랙리스트 목록응답", json.toString())

                val code = json.getInt("code")

                if(code == 200){
                    val data = json.getJSONObject("data")
                    val black_lists = data.getJSONArray("black_lists")

//                    블랙리스트 : 2개 => 0, 1
                    for(i  in 0..black_lists.length()-1){
////                        JSONArray 내부의 i 번째 JSONObject를 추출
//                        val tempJson = black_lists.getJSONObject(i)
//
////                        뽑아낸 JSONObject => BlackListData클래스로 변경(리스트 뷰에서 활용가능)
//                        val tempData = BlackListData.getBlackListDataFromjson(tempJson)
//
////                        BlackListData클래스로 변경된 변수를 ArrayList에 추가
//                        blackList.add(tempData)

                        blackList.add(BlackListData.getBlackListDataFromjson(black_lists.getJSONObject( i )) )

                    }

                    runOnUiThread {
                        blackListDataAdapter?.notifyDataSetChanged()
                    }

                }
                else{
                    val message = json.getString("message")
                    runOnUiThread {
                        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
