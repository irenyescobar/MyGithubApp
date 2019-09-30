package ireny.escobar.mygithubapp.di.components

import dagger.Subcomponent
import ireny.escobar.mygithubapp.di.modules.DetailsModule
import ireny.escobar.mygithubapp.ui.presenters.DetailsPresenter

@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {
    fun presenter(): DetailsPresenter
}