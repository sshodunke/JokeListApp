package com.smithshodunke.jokelistapp.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

/**
 *
 * Error handling api calls
 * ref: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */
suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Resource<T> {
    return withContext(dispatcher) {
        try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    throwable.printStackTrace()
                    Resource.Error(message = Constants.NETWORK_ERROR)
                }
                is HttpException -> {
                    throwable.printStackTrace()
                    val code = throwable.code()
                    Resource.Error(message = code.toString())
                }
                else -> {
                    throwable.printStackTrace()
                    Resource.Error(message = Constants.GENERIC_UNKNOWN_ERROR_MSG)
                }
            }
        }
    }
}