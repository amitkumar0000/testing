package com.demo.testing.student

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface StudentDao{

  @Query("SELECT * FROM student")
  fun getAll(): Single<List<Student>>

  @Query("SELECT name FROM student where regno is 8253122316")
  fun getCollegeStudentName(): Maybe<String>

  @Query("SELECT regno FROM student where name is 'NotFound'")
  fun getCollegeStudentReg(): Single<Long>

  @Insert
  fun insertOrUpdate(value: Student):Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrUpdate(value: List<Student>)
}