package com.turastory.aacsample

import android.app.Application
import com.turastory.aacsample.repository.GitHubCloudRepository
import com.turastory.aacsample.repository.GitHubRepository
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * Created by tura on 2018-07-17.
 */

val appModule = module {
    single { GitHubCloudRepository() as GitHubRepository }
    viewModel { GitHubRepoViewModel(get()) }
}

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}