package com.example.apk_silownia.ui.workout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apk_silownia.R
import com.example.apk_silownia.RetrofitClient
import com.example.apk_silownia.model.CreateExerciseRequest
import com.example.apk_silownia.model.Exercise
import androidx.recyclerview.widget.RecyclerView
import com.example.apk_silownia.model.ExerciseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutViewModel(application: Application) : AndroidViewModel(application) {

    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercises: LiveData<List<Exercise>> get() = _exercises

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    fun fetchExercises() {
        RetrofitClient.apiService.getExercises().enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                if (response.isSuccessful) {
                    _exercises.value = response.body()?.exercises ?: emptyList()
                } else {
                    _status.value = "Błąd pobierania ćwiczeń"
                }
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                _status.value = "Błąd połączenia"
            }
        })
    }

    fun createExercise(name: String, time: Boolean, reps: Boolean, weight: Boolean) {
        val newExercise = CreateExerciseRequest(name, time, reps, weight)

        RetrofitClient.apiService.createExercise(newExercise).enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                if (response.isSuccessful) {
                    _status.value = "Ćwiczenie dodane"
                    fetchExercises()
                } else {
                    _status.value = "Błąd dodawania ćwiczenia"
                }
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                _status.value = "Błąd połączenia"
            }
        })
    }

    // Funkcja do usuwania ćwiczenia
    fun deleteExercise(exerciseId: Int) {
        RetrofitClient.apiService.deleteExercise(exerciseId).enqueue(object : Callback<ExerciseResponse> {
            override fun onResponse(call: Call<ExerciseResponse>, response: Response<ExerciseResponse>) {
                if (response.isSuccessful) {
                    _status.value = "Ćwiczenie usunięte"
                    fetchExercises()  // Odśwież listę po usunięciu ćwiczenia
                } else {
                    _status.value = "Błąd usuwania ćwiczenia"
                }
            }

            override fun onFailure(call: Call<ExerciseResponse>, t: Throwable) {
                _status.value = "Błąd połączenia"
            }
        })
    }
}
