package com.zenlaeth.tpsup.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.zenlaeth.tpsup.R
//import com.zenlaeth.tpsup.api.ApiService
//import com.zenlaeth.tpsup.api.ServiceGenerator
//import com.zenlaeth.tpsup.models.Address
//import com.zenlaeth.tpsup.models.NewUser
//import com.zenlaeth.tpsup.models.Role
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
import okhttp3.MultipartBody

import okhttp3.RequestBody




class SignUpActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

//        var loginB = findViewById<Button>(R.id.loginBSignUp)
//        loginB.setOnClickListener {
//            val firstName = findViewById<TextView>(R.id.firstNameEtSignUp).text.toString().trim()
//            val lastName = findViewById<TextView>(R.id.lastNameEtSignUp).text.toString().trim()
//            val email = findViewById<TextView>(R.id.emailEtSignUp).text.toString().trim()
//            val password = findViewById<TextView>(R.id.passwordEtSignUp).text.toString().trim()
//            val addressNumber = findViewById<TextView>(R.id.addressNumberEtSignUp).text.toString().trim()
//            val addressName = findViewById<TextView>(R.id.addressNameEtSignUp).text.toString().trim()
//            val addressType = findViewById<TextView>(R.id.addressNameEtSignUp).text.toString().trim()
//            val addressCity = findViewById<TextView>(R.id.addressCityEtSignUp).text.toString().trim()
//            val addressZip = findViewById<TextView>(R.id.addressZipEtSignUp).text.toString().trim()
//            val birthDate = findViewById<TextView>(R.id.birthDateEtSignUp).text.toString().trim()
//            val phoneNumber = findViewById<TextView>(R.id.phoneNumberEtSignUp).text.toString().trim()
//
//            var registerList = listOf(firstName, lastName, email, password, addressNumber, birthDate, phoneNumber)
//            for(field in registerList){
//                if(field.isEmpty()){
//                    if(field == firstName) {
//                        findViewById<TextView>(R.id.firstNameEtSignUp).error = "First name required"
//                        findViewById<TextView>(R.id.firstNameEtSignUp).requestFocus()
//                    }
//                    if(field == lastName) {
//                        findViewById<TextView>(R.id.lastNameEtSignUp).error = "Last name required"
//                        findViewById<TextView>(R.id.lastNameEtSignUp).requestFocus()
//                    }
//                    if(field == email) {
//                        findViewById<TextView>(R.id.emailEtSignUp).error = "Email required"
//                        findViewById<TextView>(R.id.emailEtSignUp).requestFocus()
//                    }
//                    if(field == password) {
//                        findViewById<TextView>(R.id.passwordEtSignUp).error = "Password required"
//                        findViewById<TextView>(R.id.passwordEtSignUp).requestFocus()
//                    }
//                    if(field == addressNumber) {
//                        findViewById<TextView>(R.id.addressNumberEtSignUp).error = "Street number required"
//                        findViewById<TextView>(R.id.addressNumberEtSignUp).requestFocus()
//                    }
//                    if(field == addressName) {
//                        findViewById<TextView>(R.id.addressNameEtSignUp).error = "Street name required"
//                        findViewById<TextView>(R.id.addressNameEtSignUp).requestFocus()
//                    }
//                    if(field == addressType) {
//                        findViewById<TextView>(R.id.addressTypeEtSignUp).error = "Street type required"
//                        findViewById<TextView>(R.id.addressTypeEtSignUp).requestFocus()
//                    }
//                    if(field == addressType) {
//                        findViewById<TextView>(R.id.addressNameEtSignUp).error = "Street type required"
//                        findViewById<TextView>(R.id.addressNameEtSignUp).requestFocus()
//                    }
//                    if(field == addressCity) {
//                        findViewById<TextView>(R.id.addressCityEtSignUp).error = "City required"
//                        findViewById<TextView>(R.id.addressCityEtSignUp).requestFocus()
//                    }
//                    if(field == addressZip) {
//                        findViewById<TextView>(R.id.addressZipEtSignUp).error = "Zip code required"
//                        findViewById<TextView>(R.id.addressZipEtSignUp).requestFocus()
//                    }
//                    if(field == birthDate) {
//                        findViewById<TextView>(R.id.birthDateEtSignUp).error = "BirthDate required"
//                        findViewById<TextView>(R.id.birthDateEtSignUp).requestFocus()
//                    }
//                    if(field == phoneNumber) {
//                        findViewById<TextView>(R.id.phoneNumberEtSignUp).error = "Phone number required"
//                        findViewById<TextView>(R.id.phoneNumberEtSignUp).requestFocus()
//                    }
//                    return@setOnClickListener
//                }
//            }
//
//            val serviceGenerator = ServiceGenerator.buildService(ApiService::class.java)
//
//            var user = NewUser(firstName,
//                lastName,
//                email,
//                password,
////                Address(addressCity, addressName, addressNumber, addressType, addressZip),
//                birthDate,
//                phoneNumber,
////                Role(1)
//            )
//
//            var call = serviceGenerator.createUser(user)
//
//            call.enqueue(object : Callback<MutableList<NewUser>> {
//                override fun onResponse(call: Call<MutableList<NewUser>>, response: Response<MutableList<NewUser>>) {
//                    Toast.makeText(applicationContext, "User created successfully", Toast.LENGTH_LONG).show()
//                    Log.e("error", "good")
//                }
//
//                override fun onFailure(call: Call<MutableList<NewUser>>, t: Throwable) {
//                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//                }
//            })
//            // Handler code here.
////            val intent = Intent(this, HomeActivity::class.java)
////            this.startActivity(intent);
//        }
//
//        var AccountTv = findViewById<TextView>(R.id.AccountTvSignUp)
//        AccountTv.setOnClickListener {
//            // Handler code here.
//            val intent = Intent(this, SignInActivity::class.java)
//            this.startActivity(intent);
//        }
    }
}