package com.radlance.matule.domain.authorization

interface AuthResult {
    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapSuccess(): T
        fun mapError(noConnection: Boolean, statusCode: Int): T
    }

    object Success : AuthResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess()
        }
    }

    data class Error(
        private val noConnection: Boolean = false, private val statusCode: Int = 522
    ) : AuthResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError(noConnection, statusCode)
        }
    }
}