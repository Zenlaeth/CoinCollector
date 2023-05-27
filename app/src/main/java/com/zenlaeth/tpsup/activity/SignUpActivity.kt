package com.zenlaeth.tpsup.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.zenlaeth.tpsup.R
import com.zenlaeth.tpsup.api.FirebaseManager
import com.zenlaeth.tpsup.databinding.SignUpActivityBinding
import com.zenlaeth.tpsup.entity.User
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: SignUpActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseStore = FirebaseFirestore.getInstance()

        binding.loginBSignUp.setOnClickListener {
            val username = binding.usernameEtSignUp.text.toString()
            val email = binding.emailEtSignUp.text.toString()
            val password = binding.passwordEtSignUp.text.toString()
            val confirmPassword = binding.confirmPasswordEtSignUp.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                // username already exists
                lifecycleScope.launch {
                    val usernameList =  FirebaseManager.getUsernames()
                    onResult(
                        usernameList,
                        email,
                        username,
                        password,
                        confirmPassword
                    )
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }

        var accountTv = binding.accountTvSignUp
        accountTv.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, SignInActivity::class.java)
            this.startActivity(intent);
        }
    }

    private fun onResult(
        usernameList: ArrayList<String>,
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        if(!usernameList.contains(username) && !usernameList.contains(email)) {
            if(password == confirmPassword) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful) {
                        val currentUserId = firebaseAuth.currentUser!!.uid
                        val userToStore = User( currentUserId, username, email)

                        registerUser(userToStore)

                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            }
        } else if(usernameList.contains(username)) {
            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(userData:User) {
        firebaseStore.collection("users")
            .document()
            .set(userData).addOnSuccessListener {
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
            }
    }

}