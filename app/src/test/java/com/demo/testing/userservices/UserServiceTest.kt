package com.demo.testing.userservices

import com.demo.testing.rxjavaschedulers.RxSchedulerOverrides
import com.nhaarman.mockito_kotlin.check
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class UserServiceTest {

  @Mock
  lateinit var service:StackOverflowService

  @get:Rule
  val rxScheduler = RxSchedulerOverrides()

  lateinit internal var userService: UserService

  val userList by lazy { listOf(
    User(1),
    User(2)
  ) }



  @Before
  fun setup(){
    userService = UserService(service)
    whenever(service.topUsers)
      .thenReturn(Single.just(UserResponse(userList)))
    whenever(service.getBadges(1))
      .thenReturn(Single.just(BadgeResponse("badge")).delay(1,TimeUnit.SECONDS))
    whenever(service.getBadges(2))
      .thenReturn(Single.just(BadgeResponse("badge")).delay(2,TimeUnit.SECONDS))
  }

  @Test
  fun emptyTest(){
    userService.loadUsers()

  }

  @Test
  fun loadUsers() {
    val listUser = userService.loadUsers().blockingGet()
    assertThat(listUser, hasSize(2));
  }

  @Test
  fun testLoadUser(){
    val testObserver = userService.loadUsers().test()
    testObserver.awaitTerminalEvent()
    testObserver.assertNoErrors()
    testObserver.assertValue{
      it.size == 2
    }
  }
}