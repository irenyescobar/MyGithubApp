package ireny.escobar.mygithubapp.utils

import io.reactivex.Scheduler


interface SchedulerProvider {
    fun ui() : Scheduler
    fun io() : Scheduler
}