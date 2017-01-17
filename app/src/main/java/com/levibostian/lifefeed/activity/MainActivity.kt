package com.levibostian.lifefeed.activity

import android.app.Fragment
import com.curiosityio.andoidviews.activity.BaseActivity
import com.levibostian.lifefeed.fragment.MainFragment

class MainActivity : BaseActivity() {

    override fun getInitialFragment(): Fragment? {
        return MainFragment.newInstance()
    }

}