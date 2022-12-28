package com.example.unittesting.presentation

import com.example.unittesting.domain.GetUserNameUseCase
import com.example.unittesting.domain.SaveUserNameUseCase
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainViewModelTest {

    val getUserNameUseCase = mock<GetUserNameUseCase>()
    val saveUserNameUseCase = mock<SaveUserNameUseCase>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
    }

    @BeforeEach
    fun beforeEach() {

    }

    @Test
    fun shouldSaveUsernameReturnTrue() {

    }

    @Test
    fun shouldSaveUsernameReturnFalse() {

    }

    @Test
    fun shouldLoadData() {

    }
}