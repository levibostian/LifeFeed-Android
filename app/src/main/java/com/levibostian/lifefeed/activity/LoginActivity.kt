package com.levibostian.lifefeed.activity

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import com.curiosityio.andoidviews.activity.BaseActivity
import com.curiosityio.androidboilerplate.util.IntentUtil
import com.levibostian.lifefeed.fragment.LoginFragment

class LoginActivity : BaseActivity() {

    companion object {
        fun getIntent(context: Context): Intent {
            return IntentUtil.startActivityIntent(context, LoginActivity::class.java)
        }
    }

    interface TwitterLoginResultListener {
        fun onLoginResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    var twitterLoginResultListener: TwitterLoginResultListener? = null

    override fun getInitialFragment(): Fragment? = LoginFragment.getInstance()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        twitterLoginResultListener?.onLoginResult(requestCode, resultCode, data)
    }

}
