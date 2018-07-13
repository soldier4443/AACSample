package com.turastory.aacsample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by tura on 2018-07-13.
 */

class SimpleInputViewModel : ViewModel() {
    var text: LiveData<String>? = null
        private set
    var checked: Boolean? = null
        private set
}