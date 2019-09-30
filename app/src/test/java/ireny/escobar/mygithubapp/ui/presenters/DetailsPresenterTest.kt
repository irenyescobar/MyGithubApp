package ireny.escobar.mygithubapp.ui.presenters

import io.reactivex.Single
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.model.repositories.IssueRepositoryInterface
import ireny.escobar.mygithubapp.testutil.TestSchedulers
import ireny.escobar.mygithubapp.testutil.fakerOneIssue
import ireny.escobar.mygithubapp.ui.views.details.DetailsViewInterface
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailsPresenterTest {

    @Mock
    lateinit var mockRepo: IssueRepositoryInterface

    @Mock
    lateinit var mockView: DetailsViewInterface

    lateinit var testSchedulers: TestSchedulers

    lateinit var presenter: DetailsPresenter


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulers= TestSchedulers()
        presenter =
            DetailsPresenter(mockRepo, testSchedulers)
    }

    @Test
    fun testGetIssue_error() {

        val messageError = "No data"
        val exception = Exception(messageError)
        val issue_number = Mockito.anyLong()
        val single: Single<Issue> = Single.create {
                emitter ->
            emitter.onError(exception)
        }

        Mockito.`when`(mockRepo.getIssue(issue_number)).thenReturn(single)

        presenter.attachView(mockView)
        presenter.getIssue(issue_number)

        testSchedulers.testScheduler.triggerActions()

        Mockito.verify(mockView).hideProgressBar()
        Mockito.verify(mockView).displayError("Error: $messageError")

    }

    @Test
    fun testGetIssue_success() {

        val issue_number = Mockito.anyLong()
        val issue = fakerOneIssue(issue_number)
        val single: Single<Issue> = Single.create {
                emitter ->
            emitter.onSuccess(issue)
        }

        Mockito.`when`(mockRepo.getIssue(issue_number)).thenReturn(single)

        presenter.attachView(mockView)
        presenter.getIssue(issue_number)

        testSchedulers.testScheduler.triggerActions()

        Mockito.verify(mockView).displayIssue(issue)
        Mockito.verify(mockView).hideProgressBar()
    }

}