package ireny.escobar.mygithubapp.testutil

import com.github.javafaker.Faker
import ireny.escobar.mygithubapp.model.entites.Issue
import ireny.escobar.mygithubapp.model.entites.User
import java.util.*

val LOCALE_BRAZIL = Locale("pt", "BR")
val faker = Faker(LOCALE_BRAZIL)

fun fakerListIssue(size:Int):List<Issue>{

    val mutableList :MutableList<Issue> = mutableListOf()

    for(x in 1..size){

        val item = Issue(
            faker.number().randomNumber(),
            faker.lorem().sentence(),
            faker.lorem().paragraph(),
            faker.date().birthday(),
            "open",
            faker.internet().url(),
            User(
                faker.name().username(),
                faker.internet().avatar()
            )
        )

        mutableList.add(item)
    }

    return mutableList.toList()
}

fun fakerOneIssue(issue_number:Long): Issue {
    val item = Issue(
        issue_number,
        faker.lorem().sentence(),
        faker.lorem().paragraph(),
        faker.date().birthday(),
        "open",
        faker.internet().url(),
        User(
            faker.name().username(),
            faker.internet().avatar()
        )
    )
    return item
}