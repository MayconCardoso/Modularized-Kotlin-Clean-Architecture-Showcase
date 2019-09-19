package com.mctech.feature.arq.extentions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mctech.feature.arq.BaseActivity
import com.mctech.feature.arq.BaseFragment
import com.mctech.feature.arq.ComponentState
import kotlinx.coroutines.launch


fun <T> BaseActivity<*>.bindData(observable : LiveData<T>, block : (result : T) -> Unit) {
    lifecycleScope.launch {
        observable.observe(this@bindData, Observer {
            block(it)
        })
    }
}

fun <T> BaseFragment<*>.bindData(observable : LiveData<T>, block : (result : T) -> Unit) {
    lifecycleScope.launch {
        observable.observe(this@bindData, Observer {
            block(it)
        })
    }
}

fun <T> BaseActivity<*>.bindState(observable : LiveData<ComponentState<T>>, block : (result : ComponentState<T>) -> Unit) {
    lifecycleScope.launch {
        observable.observe(this@bindState, Observer {
            block(it)
        })
    }
}

fun <T> BaseFragment<*>.bindState(observable : LiveData<ComponentState<T>>, block : (result : ComponentState<T>) -> Unit) {
    lifecycleScope.launch {
        observable.observe(this@bindState, Observer {
            block(it)
        })
    }
}