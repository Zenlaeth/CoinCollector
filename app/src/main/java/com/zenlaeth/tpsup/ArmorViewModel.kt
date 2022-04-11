package com.zenlaeth.tpsup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class ArmorViewModel : ViewModel() {
    val data = MutableLiveData<ArmorBean?>()
    val errorMessage = MutableLiveData<String?>()
    val runInProgress = MutableLiveData<Boolean>(false)

    fun loadArmor(){
        //On reste l'ecran sur un clic du bouton
        runInProgress.postValue(true)
        errorMessage.postValue(null)
        data.postValue(null)

        //Déclenche une tache asynchrone
        thread {
            try {
                //Chercher mes données
                data.postValue(RequestUtils.loadArmor())
            } catch (e: Exception) {
                e.printStackTrace()
                //je fais évoluer mon message d'erreur
                errorMessage.postValue("Erreur : " + e.message)
            }
            runInProgress.postValue(false)
        }
    }
}