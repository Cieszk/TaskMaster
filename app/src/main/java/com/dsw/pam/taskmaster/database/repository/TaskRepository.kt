package com.dsw.pam.taskmaster.database.repository

import android.app.Application
import com.dsw.pam.taskmaster.database.TaskDatabase
import com.dsw.pam.taskmaster.database.dao.TaskDao
import com.dsw.pam.taskmaster.database.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TaskRepository(application: Application) {
    private val taskDao: TaskDao

    init {
        val database = TaskDatabase.getDatabase(application)
        taskDao = database.taskDao()
    }

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    fun update(task: Task) {
        taskDao.updateTask(task)
    }

    fun delete(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }
}