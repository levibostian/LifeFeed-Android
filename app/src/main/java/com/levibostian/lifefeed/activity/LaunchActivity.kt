package com.levibostian.lifefeed.activity

import android.app.Fragment
import android.os.Bundle
import com.curiosityio.andoidviews.activity.BaseActivity
import com.curiosityio.androidboilerplate.util.IntentUtil
import com.twitter.sdk.android.Twitter

class LaunchActivity : BaseActivity() {

    override fun getInitialFragment(): Fragment?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Twitter.getInstance().core.sessionManager.activeSession != null) {
            startActivity(MainActivity.getIntent(this))
        } else {
            startActivity(LoginActivity.getIntent(this))
        }
    }

}