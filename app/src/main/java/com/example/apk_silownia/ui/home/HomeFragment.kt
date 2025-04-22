package com.example.apk_silownia.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var workoutAdapter: WorkoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = root.findViewById(R.id.recyclerView_workouts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        workoutAdapter = WorkoutAdapter(emptyList())
        recyclerView.adapter = workoutAdapter

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.workouts.observe(viewLifecycleOwner) {
            workoutAdapter.updateWorkouts(it.take(3)) // Wyświetlamy tylko 3 ostatnie treningi
        }

        return root
    }
}
