package com.zenlaeth.tpsup.api

import com.google.firebase.firestore.FirebaseFirestore
import com.zenlaeth.tpsup.entity.Project
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

        suspend fun getProject(
            id: String
        ): Project {
            val db = FirebaseFirestore.getInstance()
            var set = Project()

            db.collection("projects")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        if(document["uid"] == id) {
                            set = document.toObject(Project::class.java)!!
                        }
                    }
                }
                .await()

            return set
        }

        suspend fun getProjects(): MutableList<Project> {
            val db = FirebaseFirestore.getInstance()
            val arrayList = mutableListOf<Project>()

            db.collection("projects")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val set = document.toObject(Project::class.java)

                        // add the userName to the list
                        arrayList.add(set)
                    }
                }
                .await()

            return arrayList
        }
    }
}