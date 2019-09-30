package ireny.escobar.mygithubapp.ui.presenters

import com.github.javafaker.Faker
import io.reactivex.Single
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.model.entites.User
import ireny.escobar.mygithubapp.model.repositories.IssueRepositoryInterface
import ireny.escobar.mygithubapp.testutil.TestSchedulers
import ireny.escobar.mygithubapp.testutil.fakerListIssue
import ireny.escobar.mygithubapp.ui.views.main.MainViewInterface
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class MainPresenterTest {


    @Mock
    lateinit var mockRepo: IssueRepositoryInterface

    @Mock
    lateinit var mockView: MainViewInterface

    lateinit var testSchedulers: TestSchedulers

    lateinit var presenter: MainPresenter

    val messageError = "No data"
    val exception = Exception(messageError)
    val singleError: Single<List<Issue>> = Single.create {
            emitter ->
        emitter.onError(exception)
    }

    val fakeList = fakerListIssue(30)
    val singleSuccess: Single<List<Issue>> = Single.create {
            emitter ->
        emitter.onSuccess(fakeList)
    }


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulers= TestSchedulers()
        presenter = MainPresenter(mockRepo, testSchedulers)
    }

    @Test
    fun testGetIssues_error() {

        Mockito.`when`(mockRepo.getIssues(1)).thenReturn(singleError)

        presenter.attachView(mockView)
        presenter.getIssues()

        testSchedulers.testScheduler.triggerActions()

        Mockito.verify(mockView).hideProgressBar()
        Mockito.verify(mockView).setEndRefresh()
        Mockito.verify(mockView).displayError("Error: $messageError")
        assert(presenter.disposable.size() > 0)
        Assert.assertEquals(presenter.page, 1)

    }

    @Test
    fun testGetIssues_success() {

        Mockito.`when`(mockRepo.getIssues(1)).thenReturn(singleSuccess)

        presenter.attachView(mockView)
        presenter.getIssues()

        testSchedulers.testScheduler.triggerActions()

        Mockito.verify(mockView).displayIssues(fakeList)
        Mockito.verify(mockView).setEndRefresh()
        Mockito.verify(mockView).hideProgressBar()

        assert(presenter.disposable.size() > 0)
        Assert.assertEquals(presenter.page, 2)
    }



    @Test
    fun testRefresh_success() {

        Mockito.`when`(mockRepo.getIssues(1)).thenReturn(singleSuccess)

        val page = 2
        presenter.page = 2
        presenter.attachView(mockView)
        presenter.refresh()

        testSchedulers.testScheduler.triggerActions()

        Mockito.verify(mockView).displayIssues(fakeList)
        Mockito.verify(mockView).setEndRefresh()
        Mockito.verify(mockView).hideProgressBar()
        assert(presenter.disposable.size() > 0)
        Assert.assertNotEquals(page +1,presenter.page)

    }

    @Test
    fun testRefresh_error() {

        Mockito.`when`(mockRepo.getIssues(1)).thenReturn(singleError)

        val page = 2
        presenter.page = 2
        presenter.attachView(mockView)
        presenter.refresh()

        testSchedulers.testScheduler.triggerActions()

        Mockito.verify(mockView).displayError("Error: $messageError")
        Mockito.verify(mockView).setEndRefresh()
        Mockito.verify(mockView).hideProgressBar()
        assert(presenter.disposable.size() > 0)
        Assert.assertNotEquals(page +1,presenter.page)

    }
}