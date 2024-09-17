package com.geeks.hw_6.ui.fragment.taskCreate

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.geeks.hw_6.R
import com.geeks.hw_6.databinding.FragmentTaskCreateBinding
import com.geeks.hw_6.models.TaskEntityUI
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar

class TaskCreateFragment : Fragment() {

    private val binding by lazy {
        FragmentTaskCreateBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<TaskCreateViewModel>()

    private var selectedTimeInMillis: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBtnCreateListener()
        binding.btnTime.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun setupBtnCreateListener() = with(binding) {
        btnCreate.setOnClickListener {
            selectedTimeInMillis?.let { timeInMillis ->
                val taskName = etDescription.text.toString()
                val description = etDescription.text.toString()
                viewModel.insertTask(
                    TaskEntityUI(
                        0,
                        taskName = taskName,
                        description = description,
                        time = timeInMillis
                    )
                )
                findNavController().popBackStack()
            } ?: Toast.makeText(requireContext(), "Выберите время", Toast.LENGTH_LONG).show()
        }
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(
            requireContext(),
            { _, selectedHour, selectedMinute ->
                selectedTimeInMillis = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    set(Calendar.MINUTE, selectedMinute)
                }.timeInMillis
            },
            hour,
            minute,
            true
        ).show()
    }
}