package com.esrakaya.userlistapp.domain.usecase

import com.esrakaya.userlistapp.data.datasource.DataSource
import com.esrakaya.userlistapp.domain.model.Person
import com.esrakaya.userlistapp.domain.model.Result
import javax.inject.Inject

class FetchPersonUseCase @Inject constructor(
    private val personDataSource: DataSource
) {
    suspend operator fun invoke(next: String?): Result<Pair<List<Person>, String?>> {
        val (response, error) = personDataSource.fetch(next)
        if (error != null) return Result.Error(error.errorDescription)

        if (response == null) return Result.Error("Response can not be null.")
        return Result.Success(response.data to response.next)
    }
}
