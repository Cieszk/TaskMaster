package com.dsw.pam.taskmaster.database.repository

import com.dsw.pam.taskmaster.database.dao.TaskDao
import com.dsw.pam.taskmaster.database.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insertTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    suspend fun updateTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.deleteTask(task)
        }
    }

    suspend fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }

    suspend fun getAllTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            taskDao.getAllTasks()
        }
    }
}
