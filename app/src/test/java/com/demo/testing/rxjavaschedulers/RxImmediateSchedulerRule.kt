package com.demo.testing.rxjavaschedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * JUnit test rule to modify the implementations returned from `Schedulers`
 *
 * How to use:
 *
 * ```
 * @get:Rule
 * val overrides = RxJava1SchedulerOverrides(...)
 *
 * @Test
 * fun `our viewmodel test`() {
 *   // do the needful
 * }
 * ```
 *
 * The rules apply(...) statement will be called for each method.
 * - Schedulers will be set
 * - Our test method will execute (this is what base.evaluate() does)
 * - Schedulers will be reset
 *
 * This is only intended to encapsulate scheduler rules for RxJava 1. If
 * you need scheduler overrides for RxJava 1 look at [RxJava2SchedulerOverrides]
 */


class RxImmediateSchedulerRule : TestRule {
  private val immediate = object : Scheduler() {
    override fun createWorker(): Worker {
      return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }
  }

  override fun apply(base: Statement, description: Description): Statement {
    return object : Statement() {
      @Throws(Throwable::class)
      override fun evaluate() {
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
        RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

        try {
          base.evaluate()
        } finally {
          RxJavaPlugins.reset()
          RxAndroidPlugins.reset()
        }
      }
    }
  }
}