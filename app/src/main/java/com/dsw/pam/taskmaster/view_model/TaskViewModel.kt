package com.dsw.pam.taskmaster.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dsw.pam.taskmaster.database.model.Task
import com.dsw.pam.taskmaster.database.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository = TaskRepository(application)

    val allTasks = repository.getAllTasks()

    fun insertTask(task: Task) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    suspend fun getTaskById(taskId: Int): Task? {
        return withContext(Dispatchers.IO) {
            repository.getTaskById(taskId)
        }
    }
}