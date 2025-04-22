package com.example.apk_silownia.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R

data class Workout(val name: String, val sets: Int, val weight: Int, val date: String)

class WorkoutAdapter(private var workouts: List<Workout>) : RecyclerView.Adapter<WorkoutAdapter.WorkoutViewHolder>() {

    class WorkoutViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.workout_name)
        val sets: TextView = view.findViewById(R.id.total_sets)
        val weight: TextView = view.findViewById(R.id.total_weight)
        val date: TextView = view.findViewById(R.id.workout_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workout, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        val workout = workouts[position]
        holder.name.text = workout.name
        holder.sets.text = "Total Sets: ${workout.sets}"
        holder.weight.text = "Total Weight(kg): ${workout.weight}"
        holder.date.text = "Date: ${workout.date}"
    }

    override fun getItemCount(): Int = workouts.size

    fun updateWorkouts(newWorkouts: List<Workout>) {
        workouts = newWorkouts
        notifyDataSetChanged()
    }
}
