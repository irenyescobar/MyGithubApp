package ireny.escobar.mygithubapp.di.components

import dagger.Component
import ireny.escobar.mygithubapp.MyGitHubApplication
import ireny.escobar.mygithubapp.di.modules.ApplicationModule
import ireny.escobar.mygithubapp.di.modules.DetailsModule
import ireny.escobar.mygithubapp.di.modules.MainModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: MyGitHubApplication)
    fun plus(mainModule: MainModule) : MainComponent
    fun plus(detailsModule: DetailsModule) : DetailsComponent
}