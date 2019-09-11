package com.mctech.kotlinlearning.di.modules

import com.mctech.kotlinlearning.platform.logger.LogcatLogger
import mctech.libraries.logger.Logger
import org.koin.dsl.module

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 */
val loggingModule = module{
    single<Logger>{LogcatLogger}
}