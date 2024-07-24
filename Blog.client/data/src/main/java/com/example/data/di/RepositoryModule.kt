package com.example.data.di

import com.example.data.repository.BlogRepositoryImpl
import com.example.domain.repository.BlogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindBlogRepository(repository: BlogRepositoryImpl): BlogRepository
}