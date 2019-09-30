package ireny.escobar.mygithubapp.model.services

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val provider: TokenProvider): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val token = "Bearer ${provider.getToken()}"

        var request: okhttp3.Request = chain.request()

        val headers = request
            .headers()
            .newBuilder()
            .add("Authorization", token )
            .build()

        request = request
            .newBuilder()
            .headers(headers)
            .build()

        return chain.proceed(request)
    }
}

interface TokenProvider {
    fun getToken(): String
}