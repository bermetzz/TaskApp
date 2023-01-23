package com.example.taskapp.data

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Pref(context: Context) {
    private val pref = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    fun isUserSeen(): Boolean{
        return pref.getBoolean(SEEN_KEY, false)
    }

    fun saveSeen(){
        pref.edit().putBoolean(SEEN_KEY, true).apply()
    }

    fun saveName(name: String){
        pref.edit().putString(NAME_KEY, name).apply()
    }

    fun getName(): String {
        return pref.getString(NAME_KEY, "").toString()
    }

    fun saveAge(age: String){
        pref.edit().putString(AGE_KEY, age).apply()
    }

    fun getAge(): String {
        return pref.getString(AGE_KEY, "").toString()
    }

    fun saveImage(image: String){
        return pref.edit().putString(IMAGE_KEY, image).apply()
    }

    fun getImage(): String {
        return pref.getString(IMAGE_KEY, "").toString()
    }

    companion object{
        const val PREF_NAME = "Task.pref"
        const val SEEN_KEY = "seen.key"
        const val NAME_KEY = "name.key"
        const val AGE_KEY = "age.key"
        const val IMAGE_KEY = "image.key"
    }
}