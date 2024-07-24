package com.example.data.mapper.base

interface Mapper<DTO, VO> {
    fun dtoToVo(dto: DTO): VO
}