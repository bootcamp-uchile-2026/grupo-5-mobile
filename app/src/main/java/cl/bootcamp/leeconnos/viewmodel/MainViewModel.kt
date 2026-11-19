package cl.bootcamp.leeconnos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.data.repositorio.AuthRepository
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import cl.bootcamp.leeconnos.data.repositorio.ThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val libroRepository: LibroRepository,
    private val themeRepository: ThemeRepository,
    private val authRepository: AuthRepository
): ViewModel() {

    val estaLogueado: StateFlow<Boolean?> = authRepository.estaLogueado
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val isDarkMode: StateFlow<Boolean?> = themeRepository.isDarkMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    //Carrito
    val itemsCarrito: StateFlow<List<Libro>> = libroRepository.carrito
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val ahorroCompra = itemsCarrito.map { listaLibros ->
        listaLibros.sumOf { libro ->
            (libro.precioAnterior - libro.precio) * libro.cantidad
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    val totalCompra = itemsCarrito.map { listaLibros ->
        listaLibros.sumOf { libro ->
            libro.precio * libro.cantidad
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    //Lista Deseados
    val itemsListaDeseados: StateFlow<List<Libro>> = libroRepository.deseados
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    //Ficha Libro
    private val _libroSeleccionado = MutableStateFlow<Libro?>(null)
    val libroSeleccionado = _libroSeleccionado.asStateFlow()

    //Snackbar
    private val _mensajeSnackbar = MutableStateFlow<String?>(null)
    val mensajeSnackbar = _mensajeSnackbar.asStateFlow()


    //Carrito

//    fun obtenerCantidadCarrito(): Int {
//
//        return itemsCarrito.value.sumOf { it.cantidad }
//    }

    fun agregarItemCarrito(libro: Libro) {

        viewModelScope.launch {
            libroRepository.agregarAlCarrito(libro, cantidad = 1)
        }
    }

    fun disminuirCantidadItemCarrito(libro: Libro) {
        viewModelScope.launch {
            if (libro.cantidad > 1) {
                libroRepository.modificarCantidadCarrito(libro.id)
            } else {
                eliminarItemCarrito(libro.id)
            }
        }
    }

    fun eliminarItemCarrito(libroId: String) {
        viewModelScope.launch {
            libroRepository.eliminarDelCarrito(libroId)
        }
    }


    //Lista de deseados
    fun agregarItemListaDeseados(libro: Libro) {
        viewModelScope.launch {
            libroRepository.toggleDeseado(libro)
        }
    }

    fun eliminarItemListaDeseados(libroId: String) {
        viewModelScope.launch {
            val libroAEliminar = itemsListaDeseados.value.find { it.id == libroId }
            if (libroAEliminar != null) {
                libroRepository.toggleDeseado(libroAEliminar)
            }
        }
    }


    //Seleccionar Libro
    fun seleccionarLibro(libro: Libro) {
        _libroSeleccionado.value = libro
    }

    //Snackbar
    fun mostrarMensajeSnackbar(mensaje: String) {
        _mensajeSnackbar.value = mensaje
    }

    fun limpiarMensajeSnackbar() {
        _mensajeSnackbar.value = null
    }

    //Cambiar modo oscuro
    fun toggleDarkMode(currentValue: Boolean) {
        viewModelScope.launch {
            themeRepository.setDarkMode(!currentValue)
        }
    }

}