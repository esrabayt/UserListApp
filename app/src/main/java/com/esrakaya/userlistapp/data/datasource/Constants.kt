package com.esrakaya.userlistapp.data.datasource

object Constants {

    // lower bound must be > 0, upper bound must be > lower bound
    val peopleCountRange: ClosedRange<Int> = 100..200

    // lower bound must be > 0, upper bound must be > lower bound
    val fetchCountRange: ClosedRange<Int> = 5..20

    // lower bound must be >= 0.0, upper bound must be > lower bound
    val lowWaitTimeRange: ClosedRange<Double> = 0.0..0.3

    // lower bound must be >= 0.0, upper bound must be > lower bound
    val highWaitTimeRange: ClosedRange<Double> = 1.0..2.0

    // must be > 0.0
    const val errorProbability = 0.05

    // must be > 0.0
    const val backendBugTriggerProbability = 0.05

    // must be > 0.0
    const val emptyFirstResultsProbability = 0.1
}
