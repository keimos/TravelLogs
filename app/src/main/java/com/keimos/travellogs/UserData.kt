package com.keimos.travellogs

import android.graphics.Bitmap
import android.util.Log
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

    fun notes() : LiveData<MutableList<Note>> = _notes
    fun addNote(n : Note) {
        val notes = _notes.value
        if (notes != null) {
            notes.add(n)
            _notes.notifyObserver()
        } else {
            Log.e(TAG, "addNote : note collection is null !!")
        }
    }

    fun deleteNote(at: Int) : Note? {
        val  note = _notes.value?.removeAt(at)
        _notes.notifyObserver()
        return note
    }

    fun resetNotes() {
        this._notes.value?.clear() //use when siging out
        _notes.notifyObserver()
    }

    // note data class
    data class Note(val id: String, var name: String, var description: String, var imageName: String? = null) {
        override fun toString(): String =name

        var image: Bitmap? = null
    }


}