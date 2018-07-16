package com.turastory.aacsample.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by tura on 2018-07-16.
 *
 * Provide json conversion extension methods using Gson
 */

private val basic = Gson()

fun <T> T.toJson(): String = basic.toJson(this)

fun <T> String.fromJson(): T = basic.fromJson(this, object : TypeToken<T>() {}.type)