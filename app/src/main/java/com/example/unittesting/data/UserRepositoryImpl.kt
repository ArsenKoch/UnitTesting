package com.example.unittesting.data

import com.example.unittesting.domain.SaveUserNameParam
import com.example.unittesting.domain.UserName
import com.example.unittesting.domain.UserRepository

class UserRepositoryImpl(private val userStorage: UserStorage) : UserRepository {

    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        val user = mapToStorage(saveParam)
        val result = userStorage.save(user)
        return result
    }

    override fun getName(): UserName {
        val user = userStorage.get()
        return mapToDomain(user)
    }

    private fun mapToDomain(user: User): UserName {
        return UserName(user.firstName, user.lastName)
    }

    private fun mapToStorage(saveParam: SaveUserNameParam): User {
        return User(saveParam.name, "")
    }
}