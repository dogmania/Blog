package com.example.domain.repository

import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.model.request.LoginRequestVo
import com.example.domain.model.response.JwtResponseVo
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun join(requestVo: AddUserRequestVo)
    suspend fun login(requestVo: LoginRequestVo): Flow<JwtResponseVo>
    suspend fun saveToken(token: String)
}