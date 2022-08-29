package com.esrakaya.userlistapp.data.datasource

import com.esrakaya.userlistapp.data.model.ProcessResult
import com.esrakaya.userlistapp.data.model.Response.FetchError
import com.esrakaya.userlistapp.data.model.Response.FetchResponse
import com.esrakaya.userlistapp.domain.model.Person
import com.esrakaya.userlistapp.utils.PeopleGen
import com.esrakaya.userlistapp.utils.RandomUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.min

@Singleton
class DataSource @Inject constructor(
    private val randomUtils: RandomUtils,
    private val peopleGen: PeopleGen
) {
    private var people: List<Person> = listOf()

    init {
        initializeData()
    }

    suspend fun fetch(next: String?) = withContext(Dispatchers.IO) {
        val processResult = processRequest(next)

        val timeMillis = (processResult.waitTime * 1000).toLong()
        delay(timeMillis)

        processResult.fetchResponse to processResult.fetchError
    }

    private fun initializeData() {
        if (people.isNotEmpty()) return

        val newPeople: ArrayList<Person> = arrayListOf()
        val peopleCount: Int = randomUtils.generateRandomInt(range = Constants.peopleCountRange)
        for (index in 0 until peopleCount) {
            val person = Person(id = index + 1, fullName = peopleGen.generateRandomFullName())
            newPeople.add(person)
        }
        people = newPeople.shuffled()
    }

    private fun processRequest(next: String?): ProcessResult<Person> {
        var error: FetchError? = null
        var response: FetchResponse<Person>? = null
        val isError = randomUtils.roll(probability = Constants.errorProbability)
        val waitTime: Double

        if (isError) {
            waitTime = randomUtils.generateRandomDouble(range = Constants.lowWaitTimeRange)
            error = FetchError(errorDescription = "Internal Server Error")
        } else {
            waitTime = randomUtils.generateRandomDouble(range = Constants.highWaitTimeRange)
            val fetchCount = randomUtils.generateRandomInt(range = Constants.fetchCountRange)
            val peopleCount = people.size
            val nextIntValue = try {
                next!!.toInt()
            } catch (ex: Exception) {
                null
            }

            if (next != null && (nextIntValue == null || nextIntValue < 0)) {
                error = FetchError(errorDescription = "Parameter error")
            } else {
                val endIndex: Int = min(peopleCount, fetchCount + (nextIntValue ?: 0))
                val beginIndex: Int = if (next == null) 0 else min(nextIntValue!!, endIndex)
                var responseNext: String? =
                    if (endIndex >= peopleCount) null else endIndex.toString()
                var fetchedPeople: ArrayList<Person> =
                    ArrayList(people.subList(beginIndex, endIndex))

                if (beginIndex > 0 && randomUtils.roll(probability = Constants.backendBugTriggerProbability)) {
                    fetchedPeople.add(0, people[beginIndex - 1])
                } else if (beginIndex == 0 && randomUtils.roll(probability = Constants.emptyFirstResultsProbability)) {
                    fetchedPeople = arrayListOf()
                    responseNext = null
                }
                response = FetchResponse(data = fetchedPeople, next = responseNext)
            }
        }

        return ProcessResult(response, error, waitTime)
    }
}