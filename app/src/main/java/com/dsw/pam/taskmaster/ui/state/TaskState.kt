package com.dsw.pam.taskmaster.ui.state

import com.dsw.pam.taskmaster.database.model.Task

sealed class TaskState {
    object Loading : TaskState()
    data class Success(val tasks: List<Task>) : TaskState()
    data class Error(val message: String) : TaskState()
}
