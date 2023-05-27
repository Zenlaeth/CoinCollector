package com.zenlaeth.tpsup.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.zenlaeth.tpsup.api.FirebaseManager
import com.zenlaeth.tpsup.databinding.SignInActivityBinding
import com.zenlaeth.tpsup.entity.User
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: SignInActivityBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SignInActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBSignIn.setOnClickListener {
            val email = binding.emailEtSignIn.text.toString()
            val password = binding.passwordEtSignIn.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                    if (it.isSuccessful) {
//                        lifecycleScope.launch {
//                            val user = FirebaseAuth.getInstance().currentUser
//
//                            user?.let {
//                                // Name, email address, and profile photo Url
//                                val name = user.displayName
//                                val email = user.email
//                                val photoUrl = user.photoUrl
//
//                                // Check if user's email is verified
//                                val emailVerified = user.isEmailVerified
//
//                                // The user's ID, unique to the Firebase project. Do NOT use this value to
//                                // authenticate with your backend server, if you have one. Use
//                                // FirebaseUser.getToken() instead.
//                                val uid = user.uid
//                            }
//
////                            val user = FirebaseManager.getCurrentUserData(firebaseAuth.currentUser!!.uid)
////                            Log.e("hihihihi", user.username)
////
////                            onResult(user)
//                        }
                        val intent = Intent(this, HomeActivity::class.java)
                        val successMessage = "Successfully registered!"

                        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }

        var noAccountTv = binding.noAccountTvSignIn
        noAccountTv.setOnClickListener {
            // Handler code here.
            val intent = Intent(this, SignUpActivity::class.java)
            this.startActivity(intent);
            finish()
        }
    }

//    private fun onResult(
//        user: User
//    ) {
//        val intent = Intent(this, HomeActivity::class.java)
//        val successMessage = "Successfully registered!"
//
//        Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()
//        startActivity(intent)
//    }
}