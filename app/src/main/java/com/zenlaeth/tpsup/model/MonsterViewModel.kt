package com.zenlaeth.tpsup.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zenlaeth.tpsup.bean.MonsterBean
import com.zenlaeth.tpsup.api.RequestUtils
import kotlin.concurrent.thread

class MonsterViewModel : ViewModel() {
    val data = MutableLiveData<MonsterBean?>()
    val errorMessage = MutableLiveData<String?>()
    val runInProgress = MutableLiveData<Boolean>(false)

    fun loadMonster(){
        //On reste l'ecran sur un clic du bouton
        runInProgress.postValue(true)
        errorMessage.postValue(null)
        data.postValue(null)

        //Déclenche une tache asynchrone
        thread {
            try {
                //Chercher mes données
                data.postValue(RequestUtils.loadMonster())
            } catch (e: Exception) {
                e.printStackTrace()
                //je fais évoluer mon message d'erreur
                errorMessage.postValue("Erreur : " + e.message)
            }
            runInProgress.postValue(false)
        }
    }
}