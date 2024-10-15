package com.dsw.pam.taskmaster.ui.view

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.dsw.pam.taskmaster.ui.state.TaskEffect
import com.dsw.pam.taskmaster.ui.state.TaskState
import com.dsw.pam.taskmaster.viewmodel.TaskViewModel

@Composable
fun TaskScreen(viewModel: TaskViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is TaskState.Loading -> {
            CircularProgressIndicator()
        }
        is TaskState.Success -> {
            val tasks = (state as TaskState.Success).tasks
            LazyColumn {
                items(tasks) { task ->
                    Text(text = task.title)
                }
            }
        }
        is TaskState.Error -> {
            val message = (state as TaskState.Error).message
            Text(text = "Error: $message")
        }
    }

    val effect by viewModel.effect.collectAsState()
    effect?.let {
        if (it is TaskEffect.ShowError) {
            Toast.makeText(LocalContext.current, it.message, Toast.LENGTH_SHORT).show()
        }
    }
}
