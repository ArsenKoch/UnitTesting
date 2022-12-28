package com.example.unittesting.presentation

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.unittesting.domain.GetUserNameUseCase
import com.example.unittesting.domain.SaveUserNameParam
import com.example.unittesting.domain.SaveUserNameUseCase
import com.example.unittesting.domain.UserName
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class MainViewModelTest {

    private val getUserNameUseCase = mock<GetUserNameUseCase>()
    private val saveUserNameUseCase = mock<SaveUserNameUseCase>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(getUserNameUseCase)
        Mockito.reset(saveUserNameUseCase)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    @Test
    fun shouldSaveUsernameReturnTrue() {

        val saveResult = true
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(testParams)).thenReturn(saveResult)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.save(testSaveText)

        val expected = "Save result = true"
        val actual = viewModel.resultLive.value

        Mockito.verify(saveUserNameUseCase, Mockito.times(1)).execute(testParams)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun shouldSaveUsernameReturnFalse() {

        val saveResult = false
        val testSaveText = "Test user name"
        val testParams = SaveUserNameParam(testSaveText)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.save(testSaveText)

        Mockito.`when`(saveUserNameUseCase.execute(testParams)).thenReturn(saveResult)

        val expected = "Save result = false"
        val actual = viewModel.resultLive.value

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun shouldLoadUsername() {
        val testUserName = UserName(
            firstName = "Test first name",
            lastName = "Test last name"
        )

        Mockito.`when`(getUserNameUseCase.execute()).thenReturn(testUserName)

        val viewModel = MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        )

        viewModel.load()

        val expected = "${testUserName.firstName} ${testUserName.lastName}"
        val actual = viewModel.resultLive.value

        Assertions.assertEquals(expected, actual)
    }
}