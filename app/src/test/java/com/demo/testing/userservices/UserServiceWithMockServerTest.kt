package com.demo.testing.userservices

import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.mockito.Mock
import retrofit2.Retrofit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class UserServiceWithMockServerTest {
  lateinit var mSubscriber: TestObserver<UserResponse>
  lateinit var mockWebServer: MockWebServer

  val userList by lazy { listOf(
    User(1),
    User(2)
  ) }


  @Before
  fun setup(){
    mSubscriber = TestObserver()
    mockWebServer = MockWebServer()
    mockWebServer.start()
  }

  @Mock
  lateinit var service:StackOverflowService

  @Test
  fun ServerCallWithError(){
    var url = "dfdf"

    var retrofit = Builder()
      .baseUrl(url)
      .addConverterFactory(GsonConverterFactory.create(Gson()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build()

    mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(UserResponse(userList))))

    var apiMgr = UserService(service)
    val listUser =  apiMgr.loadUsers().test()

    listUser.assertNoErrors()


  }


  @After
  fun close(){
    mockWebServer.close()
    mSubscriber.dispose()
  }
}

