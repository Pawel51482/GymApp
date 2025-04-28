package com.example.apk_silownia.adapter

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R
import com.example.apk_silownia.model.Exercise

class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val exerciseName: EditText = itemView.findViewById(R.id.editExerciseName)
    val exerciseCategory: TextView = itemView.findViewById(R.id.exerciseCategory)
    val saveButton: Button = itemView.findViewById(R.id.saveExerciseButton)
    val editButton: Button = itemView.findViewById(R.id.editExerciseButton)
    val deleteButton: Button = itemView.findViewById(R.id.deleteExerciseButton)

    // Funkcja do bindowania danych ćwiczenia do widoku
    fun bind(exercise: Exercise, onDeleteExercise: (Int) -> Unit, onSaveExercise: (Int, String) -> Unit, onEditExercise: (Int) -> Unit) {
        exerciseName.setText(exercise.name)
        exerciseCategory.text = exercise.category

        exerciseName.isEnabled = false
        saveButton.visibility = View.GONE
        editButton.visibility = View.VISIBLE

        editButton.setOnClickListener {
            exerciseName.isEnabled = true
            saveButton.visibility = View.VISIBLE
            editButton.visibility = View.GONE
        }

        saveButton.setOnClickListener {
            val newName = exerciseName.text.toString()
            onSaveExercise(exercise.id, newName)
            exerciseName.isEnabled = false
            saveButton.visibility = View.GONE
            editButton.visibility = View.VISIBLE
        }

        deleteButton.setOnClickListener {
            onDeleteExercise(exercise.id)
        }
    }
}