package com.levibostian.lifefeed.activity

import android.app.Fragment
import android.content.Context
import android.content.Intent
import com.curiosityio.andoidviews.activity.BaseActivity
import com.curiosityio.androidboilerplate.util.IntentUtil
import com.levibostian.lifefeed.fragment.MainFragment

class MainActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return IntentUtil.startActivityIntent(context, MainActivity::class.java)
        }
    }

    override fun getInitialFragment(): Fragment? = MainFragment.newInstance()

}