package com.example.sistemaestudiantes.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("StudentPrefs", Context.MODE_PRIVATE)

    fun saveLastStudent(matricula: String, nombre: String) {
        val editor = sharedPreferences.edit()
        editor.putString("KEY_LAST_MATRICULA", matricula)
        editor.putString("KEY_LAST_NOMBRE", nombre)
        editor.apply()
    }

    fun getLastMatricula(): String {
        return sharedPreferences.getString("KEY_LAST_MATRICULA", "") ?: ""
    }

    fun getLastNombre(): String {
        return sharedPreferences.getString("KEY_LAST_NOMBRE", "") ?: ""
    }
}