package com.mctech.feature.arq

import androidx.lifecycle.ViewModel
import mctech.libraries.logger.Logger
import org.koin.core.context.GlobalContext.get

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
abstract class BaseViewModel : ViewModel() {
    val logger : Logger by get().koin.inject()
}