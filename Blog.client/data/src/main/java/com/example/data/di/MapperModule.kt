package com.example.data.di

import com.example.data.mapper.ArticleResponseMapper
import com.example.data.mapper.JwtResponseMapper
import com.example.data.mapper.base.Mapper
import com.example.data.model.response.ArticleResponseDto
import com.example.data.model.response.JwtResponseDto
import com.example.domain.model.response.ArticleResponseVo
import com.example.domain.model.response.JwtResponseVo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object MapperModule {
    @Provides
    fun provideArticleResponseMapper(): Mapper<ArticleResponseDto, ArticleResponseVo> {
        return ArticleResponseMapper()
    }

    @Provides
    fun provideJwtResponseMapper(): Mapper<JwtResponseDto, JwtResponseVo> {
        return JwtResponseMapper()
    }
}