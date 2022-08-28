package id.hwaryun.shared.data.remote

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.hwaryun.core.BuildConfig
import id.hwaryun.shared.domain.GetUserTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient(
    val getUserTokenUseCase: GetUserTokenUseCase,
    val chuckerInterceptor: ChuckerInterceptor
) {

    inline fun <reified I> create(): I {
        val authInterceptor = Interceptor {
            val requestBuilder = it.request().newBuilder()
            runBlocking {
                getUserTokenUseCase().first { tokenResponse ->
                    val token = tokenResponse.payload
                    if (!token.isNullOrEmpty()) {
                        requestBuilder.addHeader("Authorization", "Bearer $token")
                    }
                    true
                }
            }
            it.proceed(requestBuilder.build())
        }

        // okhttp
        val okHttpClient = OkHttpClient.Builder()
            // TODO: add auth interceptor
            .addInterceptor(chuckerInterceptor)
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(I::class.java)
    }
}