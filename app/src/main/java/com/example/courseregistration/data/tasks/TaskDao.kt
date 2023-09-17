package com.example.courseregistration.data.tasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insert(word: Task)

        @Update
        suspend fun update(task: Task)

        @Delete
        suspend fun delete(word: Task)

        @Query("SELECT * FROM task_table ORDER BY id DESC")
        fun getAll(): Flow<List<Task>>
}