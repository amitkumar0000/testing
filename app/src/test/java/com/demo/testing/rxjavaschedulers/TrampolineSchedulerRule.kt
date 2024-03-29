package com.demo.testing.rxjavaschedulers

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TrampolineSchedulerRule : TestRule {
  override fun apply(base: Statement?, description: Description?): Statement {
    return object : Statement(){
      override fun evaluate() {
        RxJavaPlugins.setComputationSchedulerHandler {
          Schedulers.trampoline()
        }
        RxJavaPlugins.setIoSchedulerHandler {
          Schedulers.trampoline()
        }
        RxJavaPlugins.setInitNewThreadSchedulerHandler {
          Schedulers.trampoline()
        }
        RxJavaPlugins.setNewThreadSchedulerHandler {
          Schedulers.trampoline()
        }

        try {
          base?.evaluate()
        }finally {
          RxJavaPlugins.reset()
          RxAndroidPlugins.reset()
        }
      }
    }
  }
}