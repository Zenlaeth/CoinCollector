package com.zenlaeth.tpsup

import android.os.SystemClock
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.InputStreamReader
import java.util.*
const val URL_MONSTER = "https://mhw-db.com/monsters/1"
val client = OkHttpClient()
val gson = Gson()
class RequestUtils {
    companion object {
        fun loadMonster (city: String):
        MonsterBean{
            SystemClock.sleep(3000) // Attente 3 secondes
            val json = sendGet(URL_MONSTER.format(city))
            return if (Random().nextBoolean())
                gson.fromJson(json,
                    MonsterBean::class.java)
            else throw Exception("Je ne dirais pas que c'est un échec, je dirais que ca n'a pas marché")
        }

        fun sendGet(url: String): String {
            println("url : $url")
            //Création de la requete
            val request = Request.Builder().url(url).build()
            //Execution de la requête
            return client.newCall(request).execute().use {
                //Analyse du code retour
                if (!it.isSuccessful) {
                    throw Exception("Réponse du serveur incorrect :${it.code}")
                }
                //Résultat de la requete
                it.body?.string() ?: ""
            }
        }

        //Optimiser car il récupère un flux et non un String qu'il transmet directement à Gson
        //On a donc un seul parcourt du JSON et un seul stockage mémoire vu qu'il ne passe
        //pas par un String intermédiaire
        fun loadMonsterOpti(city: String): MonsterBean{
            val response = sendGetOpti(URL_MONSTER.format(city))
            val input = InputStreamReader(response.body?.byteStream())
            return gson.fromJson(input,
                MonsterBean::class.java)
        }
        fun sendGetOpti(url: String): Response {
            println("url : $url")
            //Création de la requete
            val request = Request.Builder().url(url).build()
            //Execution de la requête
            val response = client.newCall(request).execute()
            //Analyse du code retour
            return if (response.isSuccessful) {
                response.close()
                throw Exception("Réponse du serveur incorrect : ${response.code}")
            } else {
                response
            }
        }
    }
}