package com.example.a20191222_01_loginandsignupapi.datas

import org.json.JSONObject
import java.io.Serializable

class User(id:String,name:String,phone:String) :Serializable{
    var loginId = id // string으로 명시
    var name = name
    var phoneNum = phone

    constructor() : this("미입력","이름모름","폰번모름")

    companion object{
        fun getUserDataFromJson(json: JSONObject) : User{
            val userData = User()
//            파싱해서 데이터 채워넣기

            userData.loginId = json.getString("login_id")
            userData.name = json.getString("name")
            userData.phoneNum = json.getString("phone")

            return userData
        }
    }

}