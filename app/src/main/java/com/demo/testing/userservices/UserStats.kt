package com.demo.testing.userservices

class UserStats(user: User, badges: String) {
  companion object {
    fun create(user: User, badges: String):UserStats{
      return UserStats(user,badges)
    }
  }
}