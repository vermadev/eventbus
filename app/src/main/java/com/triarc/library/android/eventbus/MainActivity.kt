package com.triarc.library.android.eventbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.triarc.android.library.eventbus.EventBus

/**
 * Created by Devanshu Verma on 6/2/19
 */
class MainActivity: AppCompatActivity(), EventBus.Listener {

    private var eventBus: EventBus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        eventBus = EventBus.Builder.build(this)
        eventBus?.attach("Sample.Event")
        eventBus?.send("Sample.Event")

    }

    override fun onDestroy() {
        super.onDestroy()
        eventBus?.detach()
    }

    override fun onEvent(event: String, bundle: Bundle?) {
        Log.d("MainActivity", "Received $event")
    }
}