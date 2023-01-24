package com.smithshodunke.jokelistapp.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class HttpLoggingInterceptor : Interceptor {

    private companion object {
        private const val TAG = "HttpLoggingInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val request = origin.newBuilder().url(origin.url).build()
        Log.d(TAG, request.toString())
        return chain.proceed(request)
    }
}