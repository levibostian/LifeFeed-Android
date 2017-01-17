package com.levibostian.lifefeed

import com.levibostian.lifefeed.fragment.MainFragment
import com.levibostian.lifefeed.module.ApiModule
import com.levibostian.lifefeed.module.ManagerModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class, ManagerModule::class))
interface ApplicationComponent {
    fun inject(mainFragment: MainFragment)
}