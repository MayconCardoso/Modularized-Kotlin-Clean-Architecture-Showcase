package com.mctech.feature.random_joke.interaction

import com.mctech.feature.arq.UserInteraction

/**
 * @author MAYCON CARDOSO on 2019-11-19.
 */
sealed class RandomQuotationInteraction : UserInteraction {
    object LoadRandomQuotation : RandomQuotationInteraction()
}