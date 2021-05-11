package com.pharos.foryoutube.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences (
    context: Context
        ){
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    val tokenAccess: Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_AUTH]
    }

    val email: Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_AUTH_EMAIL]
    }

    val id: Flow<Int?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH_ID]
        }

    val username: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH_USERNAME]
        }

    val language: Flow<Int?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_LANGUAGE]
    }
    
    suspend fun saveAuthToken(tokenAccess: String/*, tokenRefresh: String*/){
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = tokenAccess
            /*preferences[KEY_AUTH] = tokenRefresh*/

        }
    }

    suspend fun saveUser(email: String, id: Int, username: String){
        dataStore.edit { preferences ->
            preferences[KEY_AUTH_USERNAME] = username
            preferences[KEY_AUTH_ID] = id
            preferences[KEY_AUTH_EMAIL] = email
        }
    }

    suspend fun loadLocate(langId: Int){
        dataStore.edit { preferences ->
            preferences[KEY_LANGUAGE] = langId
        }
    }

    suspend fun clear(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val KEY_AUTH_EMAIL = preferencesKey<String>("key_auth_email")
        private val KEY_AUTH_ID = preferencesKey<Int>("key_auth_id")
        private val KEY_AUTH_USERNAME = preferencesKey<String>("key_auth_username")
        private val KEY_LANGUAGE = preferencesKey<Int>("key_language")
    }
}