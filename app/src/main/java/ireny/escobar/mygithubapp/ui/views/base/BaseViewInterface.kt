package ireny.escobar.mygithubapp.ui.views.base

interface BaseViewInterface {
    fun showToast(s: String)
    fun showProgressBar()
    fun hideProgressBar()
    fun displayError(s: String)
}