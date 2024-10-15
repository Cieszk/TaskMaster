package com.dsw.pam.taskmaster.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsw.pam.taskmaster.database.model.Task
import com.dsw.pam.taskmaster.database.repository.TaskRepository
import com.dsw.pam.taskmaster.ui.state.TaskEffect
import com.dsw.pam.taskmaster.ui.state.TaskIntent
import com.dsw.pam.taskmaster.ui.state.TaskState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _state = MutableStateFlow<TaskState>(TaskState.Loading)
    val state: StateFlow<TaskState> = _state

    private val _effect = MutableStateFlow<TaskEffect?>(null)
    val effect: StateFlow<TaskEffect?> = _effect

    fun processIntent(intent: TaskIntent) {
        when (intent) {
            is TaskIntent.LoadTasks -> loadTasks()
            is TaskIntent.AddTask -> addTask(intent.task)
            is TaskIntent.DeleteTask -> deleteTask(intent.task)
        }
    }

    private fun loadTasks() {
        viewModelScope.launch {
            _state.value = TaskState.Loading
            try {
                val tasks = repository.getAllTasks()
                _state.value = TaskState.Success(tasks)
            } catch (e: Exception) {
                _state.value = TaskState.Error("Failed to load tasks")
                _effect.value = TaskEffect.ShowError("Error loading tasks")
            }
        }
    }

    private fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.insertTask(task)
                loadTasks()
            } catch (e: Exception) {
                _effect.value = TaskEffect.ShowError("Failed to add task")
            }
        }
    }

    private fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task)
                loadTasks()
            } catch (e: Exception) {
                _effect.value = TaskEffect.ShowError("Failed to delete task")
            }
        }
    }
}
