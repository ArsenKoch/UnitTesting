package com.example.unittesting.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock


class GetUserNameUseCaseTest {

    private val userRepository = mock<UserRepository>()

    @Test
    fun shouldReturnDataAsInRepository() {

        val testUserName = UserName(FIRST_NAME, LAST_NAME)
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)
        val useCase = GetUserNameUseCase(userRepository)
        val actual = useCase.execute()
        val expected = UserName(FIRST_NAME, LAST_NAME)

        Assertions.assertEquals(expected, actual)
    }

    companion object {
        const val FIRST_NAME = "first name"
        const val LAST_NAME = "last name"
    }
}