package com.keimos.travellogs

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object UserData {

    private const val TAG = "UserData"

    // signed in status
    private val _isSignedIn = MutableLiveData<Boolean>(false)
    var isSignedIn: LiveData<Boolean> = _isSignedIn

    fun setSignedIn(newValue: Boolean) {
        _isSignedIn.postValue(newValue)
    }

    //the notes storage
    private val _notes = MutableLiveData<MutableList<Note>>(mutableListOf())

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.postValue(this.value)
    }

    fun notifyObserver() {
        this._notes.notifyObserver()
    }

    // note data class
    data class Note(val id: String, var name: String, var description: String, var imageName: String? = null) {
        override fun toString(): String =name

        var image: Bitmap? = null
    }


}