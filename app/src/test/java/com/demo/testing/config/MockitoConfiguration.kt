package org.mockito.configuration.test

import androidx.annotation.NonNull
import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.configuration.DefaultMockitoConfiguration
import org.mockito.invocation.InvocationOnMock
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues
import org.mockito.stubbing.Answer

internal class MockitoConfiguration : DefaultMockitoConfiguration() {
  override fun getDefaultAnswer(): Answer<Any> {
    return object : ReturnsEmptyValues() {
      override fun answer(inv: InvocationOnMock): Any {
        val type = inv.method.returnType
        return if (type.isAssignableFrom(Observable::class.java!!)) {
          Observable.error<java.lang.RuntimeException>(createException(inv))
        } else if (type.isAssignableFrom(Single::class.java!!)) {
          Single.error<java.lang.RuntimeException>(createException(inv))
        } else {
          super.answer(inv)
        }
      }
    }
  }

  @NonNull
  private fun createException(
    invocation: InvocationOnMock
  ): RuntimeException {
    val s = invocation.toString()
    return RuntimeException(
      "No mock defined for invocation $s"
    )
  }
}