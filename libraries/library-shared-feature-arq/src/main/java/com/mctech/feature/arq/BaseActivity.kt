package com.mctech.feature.arq

import androidx.appcompat.app.AppCompatActivity
import com.mctech.features.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    val navigator: Navigator by inject { parametersOf(this) }
}