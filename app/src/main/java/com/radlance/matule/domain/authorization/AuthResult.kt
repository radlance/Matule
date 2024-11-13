package com.radlance.matule.domain.authorization

interface AuthResult {
    fun <T : Any> map(mapper: Mapper<T>): T

    interface Mapper<T : Any> {
        fun mapSuccess(): T
        fun mapError(): T
    }

    object Success : AuthResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapSuccess()
        }
    }

    object Error : AuthResult {
        override fun <T : Any> map(mapper: Mapper<T>): T {
            return mapper.mapError()
        }
    }
}