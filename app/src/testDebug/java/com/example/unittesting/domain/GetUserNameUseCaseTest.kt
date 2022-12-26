package com.example.unittesting.domain

import org.junit.Test
import org.junit.jupiter.api.Assertions


class TestRepository : UserRepository {
    override fun saveName(saveParam: SaveUserNameParam): Boolean {
        TODO("Not yet implemented")
    }

    override fun getName(): UserName {
        return UserName(firstName = "first name", lastName = "last name")
    }

}


class GetUserNameUseCaseTest {

    @Test
    fun shouldReturnDataAsInRepository() {
        val testRepository = TestRepository()
        val useCase = GetUserNameUseCase(testRepository)
        val actual = useCase.execute()
        val expected = UserName(firstName = "first name", lastName = "last name")

        Assertions.assertEquals(expected, actual)
    }
}