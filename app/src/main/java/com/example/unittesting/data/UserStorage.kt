package com.example.unittesting.data

interface UserStorage {
    fun save(user: User): Boolean

    fun get(): User
}