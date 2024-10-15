package com.dsw.pam.taskmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.dsw.pam.taskmaster.database.TaskDatabase
import com.dsw.pam.taskmaster.database.repository.TaskRepository
import com.dsw.pam.taskmaster.ui.theme.TaskMasterTheme
import com.dsw.pam.taskmaster.ui.view.TaskScreen
import com.dsw.pam.taskmaster.viewmodel.TaskViewModel
import com.dsw.pam.taskmaster.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TaskViewModel>() {
        TaskViewModelFactory(TaskRepository(TaskDatabase.getDatabase(applicationContext).taskDao()))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskMasterTheme {
                TaskScreen(viewModel)
            }
        }
    }
}
