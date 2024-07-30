package com.example.data.mapper

import com.example.data.mapper.base.Mapper
import com.example.data.model.response.JwtResponseDto
import com.example.domain.model.response.JwtResponseVo
import javax.inject.Inject

class JwtResponseMapper @Inject constructor(): Mapper<JwtResponseDto, JwtResponseVo> {
    override fun dtoToVo(dto: JwtResponseDto): JwtResponseVo {
        return JwtResponseVo(
            accessToken = dto.accessToken
        )
    }
}