package com.example.apk_silownia.ui.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R
import com.example.apk_silownia.adapter.ExerciseAdapter

class WorkoutFragment : Fragment(R.layout.fragment_workout) {

    private lateinit var workoutViewModel: WorkoutViewModel
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var exerciseNameEditText: EditText
    private lateinit var addExerciseButton: Button
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_workout, container, false)

        recyclerView = rootView.findViewById(R.id.recyclerView)
        exerciseNameEditText = rootView.findViewById(R.id.exerciseNameEditText)
        addExerciseButton = rootView.findViewById(R.id.addExerciseButton)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        workoutViewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)

        workoutViewModel.exercises.observe(viewLifecycleOwner, { exercises ->
            exerciseAdapter = ExerciseAdapter(exercises, ::onDeleteExercise, ::onEditExercise, ::onSaveExercise)
            recyclerView.adapter = exerciseAdapter
        })

        workoutViewModel.status.observe(viewLifecycleOwner, { status ->
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
        })

        workoutViewModel.fetchExercises()

        addExerciseButton.setOnClickListener {
            val name = exerciseNameEditText.text.toString()
            if (name.isNotEmpty()) {
                workoutViewModel.createExercise(name, time = false, reps = true, weight = false)
            } else {
                Toast.makeText(requireContext(), "Proszę wprowadzić nazwę ćwiczenia", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    private fun onDeleteExercise(exerciseId: Int) {
        workoutViewModel.deleteExercise(exerciseId)
    }

    private fun onEditExercise(exerciseId: Int) {
        workoutViewModel.setEditingExerciseId(exerciseId)
    }

    private fun onSaveExercise(exerciseId: Int, newName: String) {
        workoutViewModel.updateExerciseName(exerciseId, newName)
    }
}
