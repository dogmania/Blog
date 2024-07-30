package com.example.domain.usecase.auth

import com.example.domain.repository.UserRepository
import com.example.domain.usecase.base.UseCase
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, Unit> {
    override suspend fun invoke(params: String) {
        userRepository.saveToken(params)
    }
}