package com.example.data.di

import com.example.data.api.BlogService
import com.example.data.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {
    @Provides
    fun provideBlogService(retrofit: Retrofit): BlogService {
        return retrofit.create(BlogService::class.java)
    }

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
}