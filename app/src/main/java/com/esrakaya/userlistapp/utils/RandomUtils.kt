package com.esrakaya.userlistapp.utils

import javax.inject.Inject
import kotlin.random.Random

class RandomUtils @Inject constructor() {

    fun generateRandomInt(range: ClosedRange<Int>): Int {
        return Random.nextInt(range.start, range.endInclusive)
    }

    fun generateRandomDouble(range: ClosedRange<Double>): Double {
        return Random.nextDouble(range.start, range.endInclusive)
    }

    fun roll(probability: Double): Boolean {
        val random = Random.nextDouble(0.0, 1.0)
        return random <= probability
    }
}
