package com.example.myimagegenerator.services;

import android.content.Context;
import androidx.datastore.preferences.core.edit;
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext;

val Context.dataStore by preferencesDataStore(name = "USER_DATA")

        suspend fun saveToken(context: Context, tokenId: String) {
        withContext(Dispatchers.IO) {
        context.dataStore.edit { preferences ->
        preferences[stringPreferencesKey("tokenId")] = "Bearer $tokenId"
        }
        }
        }

        suspend fun getToken(context: Context): String {
        return withContext(Dispatchers.IO) {
        context.dataStore.data.map { preferences ->
        preferences[stringPreferencesKey("tokenId")].orEmpty()
        }.first()
        }
}