package com.dsw.pam.taskmaster.ui.state

sealed class TaskEffect {
    data class ShowError(val message: String) : TaskEffect()
    object NavigateToTaskDetails : TaskEffect()
}
