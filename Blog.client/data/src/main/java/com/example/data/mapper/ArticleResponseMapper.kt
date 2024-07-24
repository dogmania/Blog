package com.example.data.mapper

import com.example.data.mapper.base.Mapper
import com.example.data.model.response.ArticleResponseDto
import com.example.domain.model.response.ArticleResponseVo
import javax.inject.Inject

class ArticleResponseMapper @Inject constructor(): Mapper<ArticleResponseDto, ArticleResponseVo> {
    override fun dtoToVo(dto: ArticleResponseDto): ArticleResponseVo {
        return ArticleResponseVo(
            id = dto.id,
            title = dto.title,
            content = dto.content,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt
        )
    }
}