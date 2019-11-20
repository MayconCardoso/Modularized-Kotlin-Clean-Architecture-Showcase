package com.mctech.test.arq

import com.mctech.test.arq.rules.CoroutinesMainTestRule
import com.mctech.test.arq.rules.KoinModuleTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest

/**
 * @author MAYCON CARDOSO on 2019-09-25.
 */
@ExperimentalCoroutinesApi
abstract class BaseViewModelTest : AutoCloseKoinTest() {
    @get:Rule
    val koinRule = KoinModuleTestRule()
    @get:Rule
    val coroutinesTestRule = CoroutinesMainTestRule()
}