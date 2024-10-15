package com.dsw.pam.taskmaster.ui.state

import com.dsw.pam.taskmaster.database.model.Task

sealed class TaskIntent {
    object LoadTasks : TaskIntent()
    data class AddTask(val task: Task) : TaskIntent()
    data class DeleteTask(val task: Task) : TaskIntent()
}
