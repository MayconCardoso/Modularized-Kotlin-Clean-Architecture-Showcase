package com.mctech.feature.random_joke

import android.os.Bundle
import com.mctech.feature.arq.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author MAYCON CARDOSO on 2019-09-30.
 */
class RandomJokeActivity : BaseActivity<RandomJokeViewModel>() {
    private val jokeViewModel: RandomJokeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_joke)
    }
}
