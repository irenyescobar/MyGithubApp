package ireny.escobar.mygithubapp.testutil

import io.reactivex.schedulers.TestScheduler
import ireny.escobar.mygithubapp.utils.SchedulerProvider

class TestSchedulers() : SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override fun ui() = testScheduler
    override fun io() = testScheduler
}