package com.turastory.aacsample.persistence.cache

import java.util.*

/**
 * Created by tura on 2018-07-28.
 *
 * Naive in-memory cache implementation
 * using operator overloading..
 */

class InMemoryCache<Item> : Cache<Item> {
    companion object {
        const val EXPIRE_TIME_MS = 120000L
    }

    private val inMemoryCacheTimestamp: MutableMap<String, Long> = mutableMapOf()
    private val inMemoryCache: MutableMap<String, Item> = mutableMapOf()

    override operator fun get(index: String): Item? {
        return inMemoryCacheTimestamp[index]?.let {
            if (isExpired(it))
                null
            else
                inMemoryCache[index]
        }
    }

    override operator fun set(index: String, value: Item) {
        inMemoryCache[index] = value
        inMemoryCacheTimestamp[index] = newTimestamp()
    }

    private fun isExpired(it: Long) = newTimestamp() - EXPIRE_TIME_MS > it

    private fun newTimestamp() = Date().time
}