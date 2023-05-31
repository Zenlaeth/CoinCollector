package com.zenlaeth.tpsup.api

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.zenlaeth.tpsup.entity.Set
import com.zenlaeth.tpsup.entity.User
import kotlinx.coroutines.tasks.await

interface FirebaseManager {
    companion object {
        suspend fun getUsernames(): ArrayList<String> {
            val db = FirebaseFirestore.getInstance()
            val arrayList = ArrayList<String>()

            db.collection("users")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val userName = document["username"].toString()

                        // add the userName to the list
                        arrayList += userName
                    }

                }
                .await()

            return arrayList
        }

        suspend fun getSetsByMonster(
            idMonster: Int
        ): MutableList<Set> {
            val db = FirebaseFirestore.getInstance()
            val arrayList = mutableListOf<Set>()

            db.collection("sets")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val set = document.toObject(Set::class.java)

                        if(set.idMonster == idMonster) {
                            // add set to the list
                            arrayList.add(set)
                        }
                    }
                }
                .await()

            return arrayList
        }

        suspend fun getSet(
            id: String
        ): Set {
            val db = FirebaseFirestore.getInstance()
            var set = Set()

            db.collection("sets")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if(document["uid"] == id) {
                            set = document.toObject(Set::class.java)!!
                        }
                    }
                }
                .await()

//            db.collection("sets")
//                .document(id.toString())
//                .get()
//                .addOnSuccessListener { document ->
//                    set = document.toObject(Set::class.java)!!
//                }
//                .await()

            return set
        }

//        suspend fun getCurrentUserData(idUser: String): User {
//            val db = FirebaseFirestore.getInstance()
//            var userData = User()
//
//            db.collection("users")
//                .document(idUser)
//                .get()
//                .addOnCompleteListener  { task ->
//                    if (task.isSuccessful) {
//                        val document = task.result
//                        if (document?.exists() == true) {
//                            userData = gotUserResult(
//                                document.getString("id").toString(),
//                                document.getString("email").toString(),
//                                document.getString("username").toString()
//                            )
//                        } else {
//                            Log.d(TAG, "The document doesn't exist.")
//                        }
//                    } else {
//                        task.exception?.message?.let {
//                            Log.d(TAG, it)
//                        }
//                    }
//                }
//                .await()
//
//            Log.e("huuuuuuuuuh", userData.username)
//
//            return userData
//        }

        private fun gotUserResult(id: String, username: String, email: String): User {
            return User(id, username, email)
        }
    }
}