package com.levibostian.lifefeed.module

import android.content.Context
import android.content.SharedPreferences

import com.levibostian.lifefeed.manager.UserCredsManager
import com.levibostian.lifefeed.service.GitHubService

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module class ManagerModule(private val context: Context) {

    @Provides fun provideUserCredsManager(): UserCredsManager {
        return UserCredsManager(context)
    }

}

