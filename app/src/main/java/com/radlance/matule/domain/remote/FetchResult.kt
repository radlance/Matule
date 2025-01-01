package com.radlance.matule.domain.remote


interface FetchResult<T> {
    fun <E : Any> map(mapper: Mapper<E, T>): E

    interface Mapper<T: Any, D> {
        fun mapSuccess(data: D): T
        fun mapError(data: D?): T
        fun mapUnauthorized(): T
    }

    class Success<T>(val data: T) : FetchResult<T> {
        override fun <E : Any> map(mapper: Mapper<E, T>): E {
            return mapper.mapSuccess(data)
        }
    }

    class Error<T>(val data: T?) : FetchResult<T> {
        override fun <E : Any> map(mapper: Mapper<E, T>): E {
            return mapper.mapError(data)
        }
    }

    class Unauthorized<T> : FetchResult<T> {
        override fun <E : Any> map(mapper: Mapper<E, T>): E {
            return mapper.mapUnauthorized()
        }
    }
}