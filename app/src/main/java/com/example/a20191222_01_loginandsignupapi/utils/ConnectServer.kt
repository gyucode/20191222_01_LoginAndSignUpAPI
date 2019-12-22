package com.example.a20191222_01_loginandsignupapi.utils

import android.content.Context
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {
        //        우리가 접속할 서버의 호스트 주소(BASE_URL) => 강사의 맥북에 접속 주소
        val BASE_URL = "http://192.168.0.17:5000"

        //        회원가입 요청 함수
        fun putRequestSignUp(
            context: Context,
            inputId: String,
            inputPw: String,
            inputName: String,
            inputPhone: String,
            handler: JsonResponseHandler?
        ) {
//            이 앱이 클라이언트 역할을 할 수 있게 헤주는 클래스 객체화
            val client = OkHttpClient()

//            서버의 기능중 어느 주소로 갈지 명시 =>주소 url
            val url = "${BASE_URL}/auth"

//            서버로 들고갈 파라미터들을 미리 작성.
//            PUT메쏘드 이므로 폼 데이터에 파라미터들을 실어주자.
//            okhttp에서는 formData를 formBody라는 이름을 사용.
            val formData = FormBody.Builder()
                .add("login_id",inputId)
                .add("password",inputPw)
                .add("name",inputName)
                .add("phone",inputPhone)
                .build()  // 모두 추가 했으면 폼바디를 완성. build()

//            실제로 날아갈 요청을 작성.=> 화면을 넘어갈 대 Intent를 만드는 것과 비슷한 개념.
            val request = Request.Builder()
                .url(url)
                .put(formData)
                .build() // 리퀘스트에 필요한 데이터를 다 넣엇으면 build()로 완성

//            만든 요청을 실제로 실행(클라이언트) => startActivity와 비슷한 개념
//            요청에 대한 응답 처리 코드 =>액티비티가 실행하도록 연결
            client.newCall(request).enqueue(object :Callback{
                override fun onFailure(call: Call, e: IOException) {
//                    서버요청에 실패하면 왜 실패했는지를 로그캣으로 찍자
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
//                    서버 요청에 성공하면 응답 내용을 (=>String으로 받아서)JSON으로 가공
                    val body = response.body()!!.string()
                    val json = JSONObject(body)
                    handler?.onResponse(json)
                }

            })

        }
    }
}