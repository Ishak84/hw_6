package com.geeks.hw_6.ui.fragment.taskDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.geeks.hw_6.R
import com.geeks.hw_6.databinding.FragmentTaskDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class TaskDetailFragment : Fragment() {

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<TaskDetailViewModel>()

    private val args by navArgs<TaskDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTaskById(args.id).observe(viewLifecycleOwner) { task ->
            task?.let {
                binding.tvTaskName.text = it.taskName
                binding.tvTaskDescription.text = it.description
                binding.tvTaskTime.text = formatTime(it.time)
            }
        }
    }

    private fun formatTime(_timeInMillis: Long): String {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = _timeInMillis
        }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return String.format("%02d:%02d", hour, minute)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}