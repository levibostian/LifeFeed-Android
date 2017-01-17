package com.levibostian.lifefeed

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.gson.Gson
import com.levibostian.lifefeed.module.ApiModule
import com.levibostian.lifefeed.module.ManagerModule
import com.twitter.sdk.android.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterCore
import io.fabric.sdk.android.Fabric
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {

    companion object {
        @JvmStatic lateinit var component: ApplicationComponent
    }

    class TwitterCreds(var key: String = "", var secret: String = "")

    override fun onCreate() {
        super.onCreate()

        val core = CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()
        val creds: TwitterCreds = Gson().fromJson(resources.openRawResource(R.raw.twittercreds).bufferedReader().use { it.readText() }, TwitterCreds::class.java)
        val twitterAuth = TwitterAuthConfig(creds.key, creds.secret)

        val fabric = Fabric.Builder(this).kits(Crashlytics.Builder().core(core).build(), Twitter(twitterAuth)).debuggable(true).build()

        Fabric.with(fabric)

        component = DaggerApplicationComponent.builder()
                .apiModule(ApiModule(this))
                .managerModule(ManagerModule(this))
                .build()

        //configureRealm()
    }

    private fun configureRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(config)
    }

}
