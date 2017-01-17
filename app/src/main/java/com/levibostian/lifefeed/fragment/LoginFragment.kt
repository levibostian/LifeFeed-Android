package com.levibostian.lifefeed.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.curiosityio.andoidviews.fragment.BaseFragment
import com.levibostian.lifefeed.R
import com.levibostian.lifefeed.activity.LoginActivity
import com.levibostian.lifefeed.activity.MainActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : BaseFragment(), LoginActivity.TwitterLoginResultListener {

    companion object {
        fun getInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        (activity as? LoginActivity)?.twitterLoginResultListener = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_login, container, false)!!

        view.twitter_login_button.callback = object : Callback<TwitterSession>() {
            override fun failure(exception: TwitterException?) {
                Snackbar.make(view, exception?.message ?: getString(R.string.twitter_login_error), Snackbar.LENGTH_LONG).show()
            }

            override fun success(result: Result<TwitterSession>?) {
                startActivity(MainActivity.getIntent(activity))
            }
        }

        return view
    }

    override fun onLoginResult(requestCode: Int, resultCode: Int, data: Intent?) {
        view!!.twitter_login_button.onActivityResult(requestCode, resultCode, data)
    }

}
