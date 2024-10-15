package com.dsw.pam.taskmaster

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.dsw.pam.taskmaster.ui.home_screen.TaskScreen
import com.dsw.pam.taskmaster.ui.theme.TaskMasterTheme
import com.dsw.pam.taskmaster.view_model.TaskViewModel

class MainActivity : ComponentActivity() {
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            TaskMasterTheme {
               TaskScreen(taskViewModel, onAddTaskClick = { Log.d("TaskScreen", "Add task clicked") })
            }
        }
    }
}
