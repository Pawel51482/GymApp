package com.example.apk_silownia.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R
import com.example.apk_silownia.model.Exercise

class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val exerciseNameTextView: TextView = itemView.findViewById(R.id.exerciseName)
    private val deleteButton: Button = itemView.findViewById(R.id.deleteExerciseButton)

    // Funkcja do bindowania danych ćwiczenia do widoku
    fun bind(exercise: Exercise) {
        exerciseNameTextView.text = exercise.name

        deleteButton.setOnClickListener {
        }
    }
}
