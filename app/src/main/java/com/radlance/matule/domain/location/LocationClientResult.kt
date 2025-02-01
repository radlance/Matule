package com.radlance.matule.domain.location

import android.location.Location

interface LocationClientResult {
    fun <T: Any> map(mapper: Mapper<T>): T

    interface Mapper<T: Any> {
        fun mapSuccess(location: Location): T
        fun mapError(message: String): T
    }

    data class Success(private val location: Location) : LocationClientResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess(location)
        }
    }

    data class Error(private val message: String) : LocationClientResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(message)
        }
    }
}