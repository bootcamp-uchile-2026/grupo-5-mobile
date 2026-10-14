package cl.bootcamp.leeconnos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val libros: List<Libro>) : HomeUiState()
    data class Error(val mensaje: String) : HomeUiState()
}

class HomeViewModel(
    private val repository: LibroRepository = LibroRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        cargarLibros()
    }

    fun cargarLibros() {

        viewModelScope.launch {

            _uiState.value = HomeUiState.Loading

            val resultado = repository.obtenerLibrosHome()

            resultado.fold(
                onSuccess = { listaDeLibros ->
                    _uiState.value = HomeUiState.Success(listaDeLibros)
                },

                onFailure = { excepcion ->
                    _uiState.value = HomeUiState.Error(
                        excepcion.message ?: "Ocurrió un error inesperado"
                    )
                }
            )
        }
    }
}