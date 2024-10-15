import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dsw.pam.taskmaster.database.model.Task
import com.dsw.pam.taskmaster.ui.state.TaskIntent
import com.dsw.pam.taskmaster.ui.state.TaskState
import com.dsw.pam.taskmaster.viewmodel.TaskViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskScreen(viewModel: TaskViewModel, onAddTaskClick: () -> Unit) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(TaskIntent.LoadTasks)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTaskClick) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Task")

                val exampleTask = Task(0, "Example Task", "This is an example task description.", false)
                viewModel.processIntent(TaskIntent.AddTask(exampleTask))
            }
        }
    ) {
        when (state) {
            is TaskState.Loading -> {
                Text(text = "Loading...")
            }
            is TaskState.Success -> {
                val tasks = (state as TaskState.Success).tasks
                TaskList(tasks, onTaskClick = { task ->
                    viewModel.processIntent(TaskIntent.DeleteTask(task))
                })
            }
            is TaskState.Error -> {
                val message = (state as TaskState.Error).message
                Text(text = "Error: $message")
            }
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>, onTaskClick: (Task) -> Unit) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task, onTaskClick)
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskClick: (Task) -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onTaskClick(task) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = {  }
        )
        Text(text = task.title, modifier = Modifier.weight(1f))
    }
}
