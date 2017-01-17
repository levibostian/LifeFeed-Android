package com.levibostian.lifefeed

import android.app.Application
import com.levibostian.lifefeed.module.ApiModule
import com.levibostian.lifefeed.module.ManagerModule
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {

    companion object {
        @JvmStatic lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .apiModule(ApiModule(this))
                .managerModule(ManagerModule(this))
                .build()

        configureRealm()
    }

    private fun configureRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(config)
    }

}
