Android Kotlin Clean Architecture Learning Playground
=

[![CircleCI](https://circleci.com/gh/MayconCardoso/KotlinLearning/tree/master.svg?style=svg)](https://circleci.com/gh/MayconCardoso/KotlinLearning/tree/master)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.50-blue.svg)](http://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)


This project has been created just to practice a little bit of Kotlin and it's libraries; It has been structured in a multi-module fashion, with semantics guided by Clean Architecture; this means that high-level modules don't know anything about low-level ones. 

I gotta say that almost all developers have their favorite libraries to handle many different things. For example, many developers, including me, would use Dagger to inject their dependencies. But, the point in this project is: instead of using all the libraries that I am used to, I am going to use a different one to improve my skills. So in the Dagger case, I am going to use Koin instead and so on.

Below we have a mapper of the libraries I am used to using versus the ones that I will apply in this project.

> * Java -> Kotlin
> * Android Support Library -> AndroidX
> * RxJava -> Kotlin Coroutines
> * Dagger 2 -> Koin
> * TODO the others.

Beside of that, I'll try using all the Android Architecture Components plus Android security rules all covered of unit tests.

Again, it's just a playground project to study.

License
-

    Copyright 2019 Maycon dos Santos Cardoso

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
