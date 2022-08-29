package com.esrakaya.userlistapp.data.model

import com.esrakaya.userlistapp.data.model.Response.FetchError
import com.esrakaya.userlistapp.data.model.Response.FetchResponse

data class ProcessResult<T>(
    val fetchResponse: FetchResponse<T>?,
    val fetchError: FetchError?,
    val waitTime: Double
)
