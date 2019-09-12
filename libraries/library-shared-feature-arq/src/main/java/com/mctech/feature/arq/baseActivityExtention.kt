package com.mctech.feature.arq

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

/**
 * Create to simplify the observable subscription.
 */
fun <T> BaseActivity<*>.bindState(observable : LiveData<ComponentState<T>>, block : (result : ComponentState<T>) -> Unit) {
    lifecycleScope.launch {
        observable.observe(this@bindState, Observer {
            block(it)
        })
    }
}