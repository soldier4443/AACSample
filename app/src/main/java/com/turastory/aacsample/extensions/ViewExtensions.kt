package com.turastory.aacsample.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by tura on 2018-07-16.
 *
 * Useful View extensions
 */

fun ViewGroup.inflate(@LayoutRes item: Int): View =
    LayoutInflater.from(this.context).inflate(item, this, false)