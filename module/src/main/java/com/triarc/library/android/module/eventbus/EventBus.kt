package com.triarc.library.android.module.eventbus

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.triarc.library.android.module.eventbus.ipc.EventBusImpl

/**
 * Created by Devanshu Verma on 7/2/19
 */
interface EventBus {

    object Builder {

        fun build(activity: AppCompatActivity): EventBus {
            val listener = activity as? EventBus.Listener
            return EventBusImpl(activity.baseContext, listener)
        }

        fun build(fragment: Fragment): EventBus {
            val context = fragment.context

            if(context == null)
                throw NullPointerException()
            else {
                val listener = fragment as? EventBus.Listener
                return EventBusImpl(context, listener)
            }
        }
    }

    fun send(event: String, bundle: Bundle? = null)

    fun attach(events: List<String>)

    fun attach(event: String)

    fun detach()

    interface Listener {
        fun onEvent(event: String, bundle: Bundle? = null)
    }
}
