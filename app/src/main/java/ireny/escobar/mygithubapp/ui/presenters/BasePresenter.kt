package ireny.escobar.mygithubapp.ui.presenters

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T>{

    val disposable: CompositeDisposable = CompositeDisposable()

    var view: T? = null
        private set

    private fun detachView() {
        this.view = null
    }

    fun attachView(view: T) {
        this.view = view
    }

    fun dispose() {
        detachView()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}