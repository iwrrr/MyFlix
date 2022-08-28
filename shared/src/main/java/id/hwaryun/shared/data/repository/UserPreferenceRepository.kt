package id.hwaryun.shared.data.repository

import id.hwaryun.core.wrapper.DataResource
import id.hwaryun.shared.data.local.datastore.UserPreferenceDataSource
import id.hwaryun.shared.data.model.response.UserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

interface UserPreferenceRepository {
    suspend fun getUserToken(): Flow<DataResource<String>>
    suspend fun setUserToken(newUserToken: String): Flow<DataResource<Unit>>
    suspend fun isUserLoggedIn(): Flow<DataResource<Boolean>>
    suspend fun setUserLoginStatus(isUserLoggedIn: Boolean): Flow<DataResource<Unit>>
    suspend fun getCurrentUser(): Flow<DataResource<UserResponse>>
    suspend fun setCurrentUser(user: UserResponse): Flow<DataResource<Unit>>
    suspend fun clearData(): Flow<DataResource<Unit>>
}

class UserPreferenceRepositoryImpl(private val dataSource: UserPreferenceDataSource) :
    Repository(), UserPreferenceRepository {

    override suspend fun getUserToken(): Flow<DataResource<String>> = flow {
        emit(proceed { dataSource.getUserToken().first() })
    }

    override suspend fun setUserToken(newUserToken: String): Flow<DataResource<Unit>> = flow {
        emit(proceed { dataSource.setUserToken(newUserToken) })
    }

    override suspend fun isUserLoggedIn(): Flow<DataResource<Boolean>> = flow {
        emit(proceed { dataSource.isUserLoggedIn().first() })
    }

    override suspend fun setUserLoginStatus(isUserLoggedIn: Boolean): Flow<DataResource<Unit>> =
        flow {
            emit(proceed { dataSource.setUserLoggedStatus(isUserLoggedIn) })
        }

    override suspend fun getCurrentUser(): Flow<DataResource<UserResponse>> = flow {
        emit(proceed { dataSource.getCurrentUser().first() })
    }

    override suspend fun setCurrentUser(user: UserResponse) = flow {
        emit(proceed { dataSource.setCurrentUser(user) })
    }

    override suspend fun clearData() = flow {
        emit(proceed { dataSource.clearData() })
    }
}