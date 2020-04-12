package dev.juyoung.unsplash.data.network.interceptor

import dev.juyoung.unsplash.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class UnsplashHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().run {
            addHeader(Headers.VERSION.key, Headers.VERSION.value)
            addHeader(Headers.AUTHORIZATION.key, Headers.AUTHORIZATION.value)
            build()
        }

        return chain.proceed(request)
    }

    private companion object {
        enum class Headers(val key: String, val value: String) {
            VERSION("Accept-Version", "v1"),
            AUTHORIZATION(
                "Authorization",
                "${BuildConfig.API_AUTHORIZATION_PREFIX} ${BuildConfig.API_ACCESS_KEY}"
            )
        }
    }
}
