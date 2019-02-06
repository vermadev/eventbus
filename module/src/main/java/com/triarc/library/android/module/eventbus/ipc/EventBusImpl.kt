package com.triarc.library.android.module.eventbus.ipc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.triarc.library.android.module.eventbus.EventBus
import com.triarc.library.android.module.eventbus.controller.BroadcastController
import java.lang.ref.WeakReference
import java.util.HashMap

/**
 * Created by Devanshu Verma on 23/1/19
 */
class EventBusImpl(context: Context, private val eventListener: EventBus.Listener? = null): EventBus {

    private val TAG = EventBusImpl::class.java.simpleName

    private val eventMap: HashMap<String, String> = HashMap()

    private var context: WeakReference<Context> = WeakReference(context)

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.action?.let {event ->
                Log.d(TAG, "Received '$event' on Event Bus")
                if (eventMap.containsValue(event)) {
                    Log.d(TAG,"Delivered '$event' on Event Bus")
                    eventListener?.onEvent(event, intent.getBundleExtra(DATA_BUNDLE))
                }
            }
        }
    }

    override fun send(event: String, bundle: Bundle?) {
        val intent = Intent(event)
        if (bundle != null)
            intent.putExtra(DATA_BUNDLE, bundle)

        BroadcastController.sendLocalBroadcast(context.get(), intent)
        Log.d(TAG,"Posting '$event' on Event Bus")
    }

    override fun attach(events: List<String>) {
        events.forEach {event ->
            attach(event)
        }
    }

    override fun attach(event: String) {
        eventMap[event] = event
        BroadcastController.registerLocalReceiver(context.get(), event, receiver)
    }

    override fun detach() {
        BroadcastController.unregisterLocalReceiver(context.get(), receiver)
    }

    private companion object {
        private const val DATA_BUNDLE = "DataBundle"
    }
}
