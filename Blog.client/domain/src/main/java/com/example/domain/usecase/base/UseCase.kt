package com.example.domain.usecase.base

interface UseCase<in REQUEST, out RESPONSE> {
    suspend operator fun invoke(params: REQUEST): RESPONSE
}