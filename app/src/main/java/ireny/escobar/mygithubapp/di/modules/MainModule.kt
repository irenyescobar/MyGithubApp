package ireny.escobar.mygithubapp.di.modules

import dagger.Module
import dagger.Provides
import ireny.escobar.mygithubapp.model.repositories.IssueRepositoryInterface
import ireny.escobar.mygithubapp.ui.presenters.MainPresenter
import ireny.escobar.mygithubapp.utils.SchedulerProvider

@Module
class MainModule {
    @Provides
    fun providePresenter(issueRepository: IssueRepositoryInterface, schedulers: SchedulerProvider)
            = MainPresenter(issueRepository, schedulers)
}