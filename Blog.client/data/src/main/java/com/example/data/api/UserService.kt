package com.example.data.api

import com.example.data.model.response.JwtResponseDto
import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.model.request.LoginRequestVo
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/user")
    suspend fun join(@Body request: AddUserRequestVo)

    @POST("/api/login")
    suspend fun login(@Body request: LoginRequestVo): JwtResponseDto
}