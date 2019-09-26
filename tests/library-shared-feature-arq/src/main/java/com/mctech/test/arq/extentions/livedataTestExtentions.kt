package com.mctech.test.arq.extentions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.collectValuesWhenCoroutineBuilder(block : (List<T>) -> Unit) {
    val emittedValues = mutableListOf<T>()
    val observer = Observer<T> {
        emittedValues.add(it)
    }

    try {
        observeForever(observer)
        block(emittedValues)
    } finally {
        removeObserver(observer)
    }
}