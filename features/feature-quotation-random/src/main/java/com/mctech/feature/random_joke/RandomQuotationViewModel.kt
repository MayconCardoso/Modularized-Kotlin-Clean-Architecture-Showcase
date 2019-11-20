package com.mctech.feature.random_joke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mctech.domain.interaction.Result
import com.mctech.domain.interaction.quotation.GetRandomQuotationCase
import com.mctech.domain.model.Quotation
import com.mctech.feature.arq.BaseViewModel
import com.mctech.feature.arq.ComponentState
import com.mctech.feature.arq.UserInteraction
import com.mctech.feature.random_joke.interaction.RandomQuotationInteraction

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class RandomQuotationViewModel(
    private val getRandomCase: GetRandomQuotationCase
) : BaseViewModel(){
    private val _quotationState = MutableLiveData<ComponentState<Quotation>>(ComponentState.Initializing)
    val quotationState : LiveData<ComponentState<Quotation>> = _quotationState

    override suspend fun handleUserInteraction(interaction: UserInteraction) {
        when(interaction){
            is RandomQuotationInteraction.LoadRandomQuotation -> {
                fetchQuotation()
            }
        }
    }

    private suspend fun fetchQuotation() {
        _quotationState.value = ComponentState.Loading

        when(val result = getRandomCase.execute()){
            is Result.Failure -> {
                _quotationState.value = ComponentState.Error(result.throwable)
            }
            is Result.Success -> {
                _quotationState.value = ComponentState.Success(result.result)
            }
        }
    }
}