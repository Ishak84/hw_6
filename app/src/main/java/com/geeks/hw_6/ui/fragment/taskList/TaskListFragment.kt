package com.geeks.hw_6.ui.fragment.taskList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.geeks.domain.model.TaskEntityModel
import com.geeks.hw_6.R
import com.geeks.hw_6.databinding.FragmentTaskListBinding
import com.geeks.hw_6.models.TaskEntityUI
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskListFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskListBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<TaskListViewModel>()

    private val taskListAdapter = TaskListAdapter(onItemClick = ::onItemClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvTasksList.adapter = taskListAdapter
        setupClickListener()
        iniFetchTask()
        setupRecyclerView()
    }

    private fun iniFetchTask() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchTasks().collectLatest { tasks ->
                val sortedTasks = sortTasksByTime(tasks)
                val uiTasks = sortedTasks.map { task ->
                    TaskEntityUI(
                        taskId = task.taskId,
                        taskName = task.taskName,
                        description = task.description,
                        time = task.time
                    )
                }
                taskListAdapter.submitList(uiTasks)
            }
        }
    }

    private fun setupClickListener() {
        binding.btnAdd.setOnClickListener(::handleAddButtonClick)
    }

    private fun setupRecyclerView() {
        binding.rvTasksList.adapter = taskListAdapter

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = taskListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteTask(task.taskId)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvTasksList)
    }

    private fun onItemClick(taskId: Int) {
        findNavController().navigate(
            TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(
                taskId
            )
        )
    }

    fun handleAddButtonClick(view: View) {
        findNavController().navigate(
            TaskListFragmentDirections.actionTaskListFragmentToTaskCreateFragment(
                id
            )
        )
    }

    fun sortTasksByTime(tasks: List<TaskEntityModel>): List<TaskEntityModel> {
        return tasks.sortedWith(Comparator { t1, t2 ->
            t1.time.compareTo(t2.time)
        })
    }
}