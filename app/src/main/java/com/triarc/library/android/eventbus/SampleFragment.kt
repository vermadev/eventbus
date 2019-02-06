package com.triarc.library.android.eventbus

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.triarc.android.library.eventbus.EventBus

/**
 * Created by Devanshu Verma on 7/2/19
 */
class SampleFragment: Fragment(), EventBus.Listener {

    private var eventBus: EventBus? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_sample, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        eventBus = EventBus.Builder.build(this)
        eventBus?.attach("Sample.Event")
        eventBus?.send("Sample.Event")
    }

    override fun onDestroy() {
        super.onDestroy()
        eventBus?.detach()
    }

    override fun onEvent(event: String, bundle: Bundle?) {
        Log.d("SampleFragment", "Received $event")
    }
}