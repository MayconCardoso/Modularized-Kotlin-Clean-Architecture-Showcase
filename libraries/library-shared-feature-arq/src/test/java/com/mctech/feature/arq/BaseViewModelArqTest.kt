package com.mctech.feature.arq

import com.mctech.test.arq.BaseViewModelTest
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-26.
 */
open class BaseViewModelArqTest : BaseViewModelTest() {
    lateinit var baseViewModel: BaseViewModel

    @Before
    fun `before each test`() {
        baseViewModel = spy(BaseViewModel())
    }


    @Test
    fun `should add interaction on stack`() {
        runBlocking {
            baseViewModel.interact(MockInteraction.NoParamInteraction)
            verify(baseViewModel).suspendedInteraction(MockInteraction.NoParamInteraction)
        }
    }

    @Test
    fun `should retry last interaction`() {
        runBlocking {
            val expected = MockInteraction.ParamInteraction("")

            baseViewModel.interact(MockInteraction.NoParamInteraction)
            baseViewModel.interact(expected)
            baseViewModel.reprocessLastInteraction()
            verify(baseViewModel, times(2)).handleUserInteraction(expected)


            baseViewModel.interact(MockInteraction.NoParamInteraction)
            baseViewModel.reprocessLastInteraction()
            verify(baseViewModel, times(3)).handleUserInteraction(MockInteraction.NoParamInteraction)
        }
    }

}

sealed class MockInteraction : UserInteraction {
    object NoParamInteraction : MockInteraction()
    data class ParamInteraction(val foo: String) : MockInteraction()
}