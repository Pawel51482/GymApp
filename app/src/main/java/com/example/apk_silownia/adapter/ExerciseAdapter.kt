package com.example.apk_silownia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R
import com.example.apk_silownia.model.Exercise

class ExerciseAdapter(
    private val exercises: List<Exercise>,
    private val onDeleteExercise: (Int) -> Unit,
    private val onEditExercise: (Int) -> Unit,
    private val onSaveExercise: (Int, String) -> Unit
) : RecyclerView.Adapter<ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise, onDeleteExercise, onSaveExercise, onEditExercise)
    }

    override fun getItemCount() = exercises.size
}
