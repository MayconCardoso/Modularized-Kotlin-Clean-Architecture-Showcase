package com.mctech.feature.arq

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.mctech.features.navigation.Navigator
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    val navigator : Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        declareActivityDependencies()
    }

    override fun onDestroy() {
        undeclareActivityDependencies()
        super.onDestroy()
    }

    private fun declareActivityDependencies() {
        val activityModule = module {
            scope(named<BaseActivity<VM>>()) {
                factory<AppCompatActivity>(override = true) { this@BaseActivity }
            }
        }

        loadKoinModules(activityModule)
    }

    private fun undeclareActivityDependencies() {
        currentScope.close()
    }
}