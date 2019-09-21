package com.mctech.feature.arq.extentions

import android.view.View


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.setVisibilityByState(visible: Boolean) {
    if (visible) show()
    else hide()
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun View.enableByState(enabled: Boolean) {
    if (enabled) enable()
    else disable()
}