package com.triarc.android.library.eventbus.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager

/**
 * Created by Devanshu Verma on 23/1/19
 */
object BroadcastController {

    fun sendLocalBroadcast(context: Context?, intent: Intent) {
        context?.let {
            LocalBroadcastManager.getInstance(it).sendBroadcast(intent)
        }
    }

    fun registerLocalReceiver(context: Context?, filter: String, receiver: BroadcastReceiver) {
        context?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(receiver, IntentFilter(filter))
        }
    }

    fun unregisterLocalReceiver(context: Context?, receiver: BroadcastReceiver) {
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(receiver)
        }
    }

    fun registerContextReceiver(context: Context?, filter: String, receiver: BroadcastReceiver) {
        context?.let {
            it.registerReceiver(receiver, IntentFilter(filter))
        }
    }

    fun unregisterContextReceiver(context: Context?, receiver: BroadcastReceiver) {
        context?.let {
            it.unregisterReceiver(receiver)
        }
    }
}
