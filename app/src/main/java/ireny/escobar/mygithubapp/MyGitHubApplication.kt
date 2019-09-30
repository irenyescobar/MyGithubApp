package ireny.escobar.mygithubapp

import android.app.Application
import ireny.escobar.mygithubapp.di.components.ApplicationComponent
import ireny.escobar.mygithubapp.di.components.DaggerApplicationComponent
import ireny.escobar.mygithubapp.di.modules.ApplicationModule


class MyGitHubApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initAppComponent()
        component.inject(this)
    }

    private fun initAppComponent() {
        component = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}