package ireny.escobar.mygithubapp.di.components

import dagger.Subcomponent
import ireny.escobar.mygithubapp.di.modules.MainModule
import ireny.escobar.mygithubapp.ui.presenters.MainPresenter

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun presenter(): MainPresenter
}