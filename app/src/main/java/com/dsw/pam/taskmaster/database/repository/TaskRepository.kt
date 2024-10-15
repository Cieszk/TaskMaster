package com.dsw.pam.taskmaster.database.repository

import com.dsw.pam.taskmaster.database.dao.TaskDao
import com.dsw.pam.taskmaster.database.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun getTaskById(taskId: Int): Task? {
        return taskDao.getTaskById(taskId)
    }

    fun getAllTasks(): List<Task> {
        return taskDao.getAllTasks()
    }
}
