package com.turastory.aacsample.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by tura on 2018-07-16.
 *
 * Provide json conversion extension methods using Gson
 */

val basicGsonImpl = Gson()

fun <T> T.toJson(): String = basicGsonImpl.toJson(this)

// This is not working.. Because of type erasure, the type information is lost at Runtime.
fun <T> String.fromJsonIncorrect(): T = basicGsonImpl.fromJson(this, object : TypeToken<T>() {}.type)

// This is working. Because this function is inlined, and therefor
inline fun <reified T> String.fromJson(): T = basicGsonImpl.fromJson(this, object : TypeToken<T>() {}.type)