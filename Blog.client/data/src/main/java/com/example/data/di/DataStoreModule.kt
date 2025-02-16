package com.example.data.di

import android.content.Context
import com.example.data.util.BlogDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun provideBlogDataStore(@ApplicationContext context: Context) : BlogDataStore {
        return BlogDataStore(context)
    }
}