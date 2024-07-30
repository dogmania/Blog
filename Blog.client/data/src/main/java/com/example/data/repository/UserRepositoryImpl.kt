package com.example.data.repository

import android.util.Log
import com.example.data.datasource.UserDataSource
import com.example.data.mapper.JwtResponseMapper
import com.example.data.util.BlogDataStore
import com.example.domain.model.request.AddUserRequestVo
import com.example.domain.model.request.LoginRequestVo
import com.example.domain.model.response.JwtResponseVo
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val jwtResponseMapper: JwtResponseMapper,
    private val blogDataStore: BlogDataStore
): UserRepository {
    override suspend fun join(requestVo: AddUserRequestVo) {
        kotlin.runCatching {
            userDataSource.join(requestVo)
        }.onFailure { e ->
            Log.e("join", e.stackTraceToString())
        }
    }

    override suspend fun login(requestVo: LoginRequestVo): Flow<JwtResponseVo> {
        return userDataSource.login(requestVo)
            .map {
                jwtResponseMapper.dtoToVo(it)
            }
            .catch { e ->
                Log.e("login", e.stackTraceToString())
                emit(JwtResponseVo())
            }
    }

    override suspend fun saveToken(token: String) {
        blogDataStore.saveAccessToken(token)
    }
}