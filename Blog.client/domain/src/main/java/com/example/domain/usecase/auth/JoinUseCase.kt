package com.example.domain.usecase.auth

import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.base.UseCase
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<AddUserRequestVo, Unit> {
    override suspend fun invoke(params: AddUserRequestVo) {
        userRepository.join(params)
    }
}