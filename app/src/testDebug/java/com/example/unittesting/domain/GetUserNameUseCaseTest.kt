package com.example.unittesting.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock


class GetUserNameUseCaseTest {

    val userRepository = mock<UserRepository>()

    @Test
    fun shouldReturnDataAsInRepository() {

        val testUserName = UserName("first name", "last name")
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)
        val useCase = GetUserNameUseCase(userRepository)
        val actual = useCase.execute()
        val expected = UserName(firstName = "first name", lastName = "last name")

        Assertions.assertEquals(expected, actual)
    }
}