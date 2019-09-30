package ireny.escobar.mygithubapp.di.modules

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ireny.escobar.mygithubapp.BuildConfig
import ireny.escobar.mygithubapp.MyGitHubApplication
import ireny.escobar.mygithubapp.model.repositories.IssueRepository
import ireny.escobar.mygithubapp.model.repositories.IssueRepositoryInterface
import ireny.escobar.mygithubapp.model.services.AuthInterceptor
import ireny.escobar.mygithubapp.model.services.IssueService
import ireny.escobar.mygithubapp.model.services.TokenProvider
import ireny.escobar.mygithubapp.utils.AppSchedulers
import ireny.escobar.mygithubapp.utils.SchedulerProvider
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(val application: MyGitHubApplication) {

    @Provides
    @Singleton
    fun provideAppContext(): Context = application

    /*fun getTokenProvider(): TokenProvider = object :
        TokenProvider {
        override fun getToken(): String {
            return ""
        }
    }*/

    //val interceptor = AuthInterceptor(getTokenProvider())
    //val okhttp = OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.API_BASE_URL)
        //.client(okhttp)
        .build()

    @Provides
    @Singleton
    fun provideIssueRepository(retrofit: Retrofit): IssueRepositoryInterface {
        return IssueRepository(retrofit.create(IssueService::class.java))
    }

    @Provides
    @Singleton
    fun provideSchedulers() : SchedulerProvider = AppSchedulers()
}