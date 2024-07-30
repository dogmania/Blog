package com.example.data.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "blog_prefs")

class BlogDataStore(context: Context) {
    private val dataStore = context.dataStore

    companion object {
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }


    val accessToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }

    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
        }
    }
}