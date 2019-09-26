package com.mctech.feature.arq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mctech.libraries.logger.Logger
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext.get

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
abstract class BaseViewModel : ViewModel() {
    val logger: Logger by get().koin.inject()

    private var userFlowInteraction = mutableListOf<UserInteraction>()

    fun interact(userInteraction: UserInteraction) {
        viewModelScope.launch {
            suspendedInteraction(userInteraction)
        }
    }

    suspend fun suspendedInteraction(userInteraction: UserInteraction) {
        userFlowInteraction.add(userInteraction)
        handleUserInteraction(userInteraction)
    }

    protected open suspend fun handleUserInteraction(interaction: UserInteraction) = Unit

    fun reprocessLastInteraction() {
        viewModelScope.launch {
            handleUserInteraction(userFlowInteraction.last())
        }
    }

    override fun onCleared() {
        userFlowInteraction.clear()
        super.onCleared()
    }
}