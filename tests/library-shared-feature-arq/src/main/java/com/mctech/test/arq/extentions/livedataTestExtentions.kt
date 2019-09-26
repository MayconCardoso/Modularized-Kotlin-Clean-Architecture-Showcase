package com.mctech.test.arq.extentions

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.runBlocking

fun <T> LiveData<T>.collectValuesForTesting(assertion: (List<T>) -> Unit) {
    test { assertion(it) }
}

fun <T> LiveData<T>.test(
    scenario: suspend () -> Unit = {},
    action: () -> Unit = {},
    assertion: (List<T>) -> Unit
) {
    val emittedValues = mutableListOf<T>()
    val observer = Observer<T> {
        emittedValues.add(it)
    }

    try {
        runBlocking {
            scenario()
            observeForever(observer)
            action()
            assertion(emittedValues)
        }
    } finally {
        removeObserver(observer)
    }
}

fun <T> LiveData<T>.assertNoValue() {
    collectValuesForTesting { it.assertEmpty() }
}