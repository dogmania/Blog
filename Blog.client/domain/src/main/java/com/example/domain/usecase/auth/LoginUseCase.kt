package com.example.domain.usecase.auth

import com.example.domain.model.request.LoginRequestVo
import com.example.domain.model.response.JwtResponseVo
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<LoginRequestVo, Flow<JwtResponseVo>> {
    override suspend fun invoke(params: LoginRequestVo): Flow<JwtResponseVo> {
        return userRepository.login(params)
    }
}