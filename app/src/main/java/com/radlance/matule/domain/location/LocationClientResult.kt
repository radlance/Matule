package com.radlance.matule.domain.location

import android.location.Address

interface LocationClientResult {
    fun <T: Any> map(mapper: Mapper<T>): T

    interface Mapper<T: Any> {
        fun mapSuccess(address: Address): T
        fun mapError(message: String): T
    }

    data class Success(private val address: Address) : LocationClientResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess(address)
        }
    }

    data class Error(private val message: String) : LocationClientResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(message)
        }
    }
}