package com.example.unittesting.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class SaveUserNameUseCaseTest {

    private val userRepository = mock<UserRepository>()

    @Test
    fun shouldNotSaveData() {

        val useCase = SaveUserNameUseCase(userRepository)

        val testUserName = UserName(
            GetUserNameUseCaseTest.FIRST_NAME,
            GetUserNameUseCaseTest.LAST_NAME
        )
        Mockito.`when`(userRepository.getName()).thenReturn(testUserName)
        val expected = true
        val testParam = SaveUserNameParam(GetUserNameUseCaseTest.FIRST_NAME)
        val actual = useCase.execute(testParam)
        Assertions.assertEquals(expected, actual)
        Mockito.verify(userRepository, Mockito.never()).saveName(any())
    }
}