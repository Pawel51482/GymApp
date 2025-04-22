package com.example.apk_silownia.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R
import com.example.apk_silownia.model.Exercise

class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Upewnij się, że masz odpowiednie ID w pliku layoutu
    private val exerciseName: TextView = view.findViewById(R.id.exerciseName)
    private val exerciseCategory: TextView = view.findViewById(R.id.exerciseCategory)

    fun bind(exercise: Exercise) {
        exerciseName.text = exercise.name  // Bindowanie danych
        exerciseCategory.text = exercise.category

        exerciseName.setTextColor(android.graphics.Color.WHITE)
        exerciseCategory.setTextColor(android.graphics.Color.WHITE)
    }
}
