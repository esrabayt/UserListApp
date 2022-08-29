package com.esrakaya.userlistapp.utils

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleGen @Inject constructor(
    private val randomUtils: RandomUtils
) {

    private val firstNames = listOf(
        "Fatma",
        "Mehmet",
        "Ayşe",
        "Mustafa",
        "Emine",
        "Ahmet",
        "Hatice",
        "Ali",
        "Zeynep",
        "Hüseyin",
        "Elif",
        "Hasan",
        "İbrahim",
        "Can",
        "Murat",
        "Özlem"
    )

    private val lastNames = listOf(
        "Yılmaz",
        "Şahin",
        "Demir",
        "Çelik",
        "Şahin",
        "Öztürk",
        "Kılıç",
        "Arslan",
        "Taş",
        "Aksoy",
        "Barış",
        "Dalkıran"
    )

    fun generateRandomFullName(): String {
        val firstNamesCount = firstNames.size
        val lastNamesCount = lastNames.size
        val firstName = firstNames[randomUtils.generateRandomInt(range = 0 until firstNamesCount)]
        val lastName = lastNames[randomUtils.generateRandomInt(range = 0 until lastNamesCount)]
        return "$firstName $lastName"
    }
}