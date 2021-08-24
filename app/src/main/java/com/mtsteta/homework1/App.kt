package com.mtsteta.homework1

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mtsteta.homework1.database.AppDatabase


class App: Application() {

    val apiService: ApiService by lazy { ApiService.create() }

    companion object {
        var prefs: SharedPreferences? = null
        var database: AppDatabase? = null
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppDatabase.initDatabase(this)
        database = AppDatabase.getInstance()

        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        prefs = EncryptedSharedPreferences.create(
            "prefs",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}