package com.demo.testing.userservices

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface StackOverflowService {

  @get:GET("/users")
  val topUsers: Single<UserResponse>

  @GET("/users/{userId}/badges")
  fun getBadges(@Path("userId") userId: Int): Single<BadgeResponse>
}