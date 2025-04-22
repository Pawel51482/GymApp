package com.example.apk_silownia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _workouts = MutableLiveData<List<Workout>>().apply {
        value = listOf(
            Workout("Trening Siłowy", 8, 10000, "12.01.2023"),
            Workout("Kardio", 5, 2000, "11.01.2023"),
            Workout("Full Body", 10, 12000, "10.01.2023"),
            Workout("Trening Nóg", 6, 8000, "09.01.2023") // Nadmiarowy, by testować sortowanie
        )
    }
    val workouts: LiveData<List<Workout>> = _workouts
}
