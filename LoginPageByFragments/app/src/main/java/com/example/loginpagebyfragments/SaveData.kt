package com.example.info_page

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class SaveData(context: Context ) {
    private var share: SharedPreferences = context.getSharedPreferences("userInformation",Context.MODE_PRIVATE)

    fun saveUserInfo(fullName: String ,userName:String,email:String,password:String,gender:String ) {
        var editor:SharedPreferences.Editor = share.edit()
        editor.putString("full_name",fullName)
        editor.putString("user_name",userName)
        editor.putString("email",email)
        editor.putString("password",password)
        editor.putString("gender",gender)
        editor.apply()
    }
    fun getFullName(): String? {
        return share.getString("full_name","")
    }
    fun getUserName(): String?{
        return share.getString("user_name","")
    }
    fun getEmail(): String?{
        return share.getString("email","")
    }
    fun getPassword(): String?{
        return share.getString("password","")
    }
    fun getGender(): String?{
        return share.getString("gender","")
    }
}