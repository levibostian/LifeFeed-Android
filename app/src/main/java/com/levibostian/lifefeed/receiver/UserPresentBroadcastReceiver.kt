package com.levibostian.lifefeed.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.curiosityio.androidboilerplate.manager.SharedPreferencesManager
import com.curiosityio.androidboilerplate.util.DateUtil
import com.levibostian.lifefeed.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.content.ContextCompat.startActivity
import java.net.URLEncoder

class UserPresentBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action == Intent.ACTION_USER_PRESENT) {
            val lastWakeUpTweetSentString = SharedPreferencesManager.getString(context!!, context.getString(R.string.preferences_last_wake_up_tweet_date))

            if (lastWakeUpTweetSentString == null || !DateUtil.isToday(SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).parse(lastWakeUpTweetSentString))) {
                val cal = Calendar.getInstance()
                cal.time = Date()
                if (cal.get(Calendar.HOUR_OF_DAY) >= 3) { // is past 3am
                    val dateNow = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date())
                    SharedPreferencesManager.edit(context).setString(context.getString(R.string.preferences_last_wake_up_tweet_date), dateNow).commit()

                    val currentTime = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date())
                    val tweetUrl = "https://twitter.com/intent/tweet?text=" + URLEncoder.encode("Just woke up! Time to start the day. " + currentTime, "UTF-8")
                    val sendTweetIntent = Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl))
                    sendTweetIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(context, sendTweetIntent, null)
                }
            }
        }
    }

}