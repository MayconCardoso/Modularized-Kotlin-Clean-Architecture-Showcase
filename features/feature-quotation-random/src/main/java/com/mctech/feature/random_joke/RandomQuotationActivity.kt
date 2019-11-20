package com.mctech.feature.random_joke

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.mctech.domain.model.Quotation
import com.mctech.feature.arq.BaseActivity
import com.mctech.feature.arq.ComponentState
import com.mctech.feature.arq.extentions.bindState
import com.mctech.feature.random_joke.databinding.ActivityRandomJokeBinding
import com.mctech.feature.random_joke.interaction.RandomQuotationInteraction
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class RandomQuotationActivity : BaseActivity<RandomQuotationViewModel>() {
    private val quotationViewModel : RandomQuotationViewModel by viewModel()
    private var binding: ActivityRandomJokeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_random_joke)

        bindState(quotationViewModel.quotationState){
            renderUi(it)
        }
    }

    private fun renderUi(state: ComponentState<Quotation>) {
        when(state){
            is ComponentState.Initializing->{
                fetchQuotation()
            }

            is ComponentState.Loading -> {
                binding?.loading = true
            }

            is ComponentState.Error -> {
                binding?.loading = false
            }

            is ComponentState.Success -> {
                binding?.loading = false
                binding?.quotation = state.result
            }
        }
    }

    private fun fetchQuotation() {
        quotationViewModel.interact(RandomQuotationInteraction.LoadRandomQuotation)
    }

    fun onClickFetchQuotation(view: View) {
        fetchQuotation()
    }
}
