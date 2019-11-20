package com.mctech.library.networking

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author MAYCON CARDOSO on 2019-09-28.
 */
object RetrofitBuilder {

    operator fun invoke(apiURL: String, httpClient: OkHttpClient) =
        with(Retrofit.Builder()) {
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

            baseUrl(apiURL)
            client(httpClient)
            addConverterFactory(GsonConverterFactory.create(gson))
            build()
        }
}