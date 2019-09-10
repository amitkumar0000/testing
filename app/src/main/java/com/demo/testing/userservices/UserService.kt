package com.demo.testing.userservices

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

internal class UserService(private val service: StackOverflowService) {

  fun loadUsers(): Single<List<UserStats>> {
    return service.topUsers
      .flattenAsObservable{
        it.items
      }
      .take(5)
      .flatMapSingle{
        this.loadUserStats(it)
      }
      .toList()
  }

  private fun loadUserStats(user: User): Single<UserStats> {
    return service.getBadges(user.id)
      .subscribeOn(Schedulers.io())
      .map{
        it.items
      }
      .map{ badges -> UserStats.create(user, badges) }
  }
}