package cl.bootcamp.leeconnos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.local.database.AppDatabase
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import cl.bootcamp.leeconnos.data.api.ApiClient.apiService
import retrofit2.Retrofit

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val recomendadosLibreros: List<Libro>,
        val masVendidos: List<Libro>,
        val masComentados: List<Libro>,
        val nuevosLanzamientos: List<Libro>
    ) : HomeUiState()
    data class Error(val mensaje: String) : HomeUiState()
}

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: LibroRepository

    init {

        val database = AppDatabase.getInstance(application)

        repository = LibroRepository(
            api = apiService,
            libroDao = database.libroDao()
        )
    }

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        cargarLibros()
    }

    fun cargarLibros() {

        viewModelScope.launch {

            _uiState.value = HomeUiState.Loading

            val resultado = repository.obtenerLibrosHome()

            try {
                //Lógica solo para hito 2, muestra listas de items distintas en carruseles, no la misma duplicada
                val peticionPage1 = async { repository.obtenerLibrosHome(page = 1) }
                val peticionPage2 = async { repository.obtenerLibrosHome(page = 2) }
                val peticionPage3 = async { repository.obtenerLibrosHome(page = 3) }
                val peticionPage4 = async { repository.obtenerLibrosHome(page = 4) }

                val recomendados = peticionPage1.await().getOrDefault(emptyList())
                val masVendidos = peticionPage2.await().getOrDefault(emptyList())
                val masComentados = peticionPage3.await().getOrDefault(emptyList())
                val nuevosLanzamientos = peticionPage4.await().getOrDefault(emptyList())

                if (recomendados.isNotEmpty() || masVendidos.isNotEmpty()) {
                    _uiState.value = HomeUiState.Success(
                        recomendadosLibreros = recomendados,
                        masVendidos = masVendidos,
                        masComentados = masComentados,
                        nuevosLanzamientos = nuevosLanzamientos
                    )
                } else {
                    _uiState.value = HomeUiState.Error("No se pudieron cargar los libros.")
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error("Ocurrió un error inesperado")
            }
        }
    }
}