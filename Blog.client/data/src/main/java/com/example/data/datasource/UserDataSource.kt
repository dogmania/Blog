package com.example.data.datasource

import com.example.data.api.UserService
import com.example.data.model.response.JwtResponseDto
import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.model.request.LoginRequestVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun join(request: AddUserRequestVo) {
        userService.join(request)
    }

    suspend fun login(request: LoginRequestVo) : Flow<JwtResponseDto> = flow {
        emit(userService.login(request))
    }
}