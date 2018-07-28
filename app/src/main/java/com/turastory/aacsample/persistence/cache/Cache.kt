package com.turastory.aacsample.persistence.cache

/**
 * Created by tura on 2018-07-28.
 *
 * Represents local cache.
 */

interface Cache<Item> {
    operator fun get(index: String): Item?
    operator fun set(index: String, value: Item)
}