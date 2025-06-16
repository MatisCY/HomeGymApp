package com.matischowyuen.homegymapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matischowyuen.homegymapp.model.ExerciseBean
import com.matischowyuen.homegymapp.network.ExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {

    private val _exercises = MutableStateFlow<List<ExerciseBean>>(emptyList())
    val exercises: StateFlow<List<ExerciseBean>> = _exercises

    var currentMuscle: String = "abs"
        private set

    fun loadExercises(muscle: String) {
        currentMuscle = muscle
        viewModelScope.launch {
            _exercises.value = ExerciseRepository.loadExercises(muscle)
        }
    }

    /** tri  **/
    fun sortByDifficulty() {
        _exercises.value = _exercises.value.sortedBy { it.difficulty }
    }
    fun sortByEquipment() {
        _exercises.value = _exercises.value.sortedBy { it.equipment }
    }
}
