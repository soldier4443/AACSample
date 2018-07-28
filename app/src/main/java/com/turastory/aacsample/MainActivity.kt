package com.turastory.aacsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.run {
            // Is there anything I can do here?
        } ?: let {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, RepoListFragment(), RepoListFragment.TAG)
                .commit()
        }
    }
}
