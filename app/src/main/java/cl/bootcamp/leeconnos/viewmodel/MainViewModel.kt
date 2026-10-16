package cl.bootcamp.leeconnos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.local.datastore.ThemePreferences
import cl.bootcamp.leeconnos.data.modelos.Libro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val themePreferences = ThemePreferences(context = application.applicationContext)

    val isDarkMode: StateFlow<Boolean> = themePreferences.isDarkMode
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    //Carrito
    private val _itemsCarrito = MutableStateFlow<List<Libro>>(emptyList())
    val itemsCarrito = _itemsCarrito.asStateFlow()

    val ahorroCompra = _itemsCarrito.map { listaLibros ->
        listaLibros.sumOf { libro ->
            (libro.precioAnterior - libro.precio) * libro.cantidad
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    val totalCompra = _itemsCarrito.map { listaLibros ->
        listaLibros.sumOf { libro ->
            libro.precio * libro.cantidad
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    //Lista Deseados
    private val _itemsListaDeseados = MutableStateFlow<List<Libro>>(emptyList())
    val itemsListaDeseados = _itemsListaDeseados.asStateFlow()

    //Ficha Libro
    private val _libroSeleccionado = MutableStateFlow<Libro?>(null)
    val libroSeleccionado = _libroSeleccionado.asStateFlow()

    //Snackbar
    private val _mensajeSnackbar = MutableStateFlow<String?>(null)
    val mensajeSnackbar = _mensajeSnackbar.asStateFlow()


    //Carrito
    fun agregarItemCarrito(libro: Libro) {

        _itemsCarrito.update { listaActual ->

            val existe = listaActual.any { it.id == libro.id }

            if (existe) {

                listaActual.map { item ->

                    if (item.id == libro.id) {

                        val nuevaCantidad = if (item.cantidad < 99) item.cantidad + 1 else 99

                        item.copy(cantidad = nuevaCantidad)

                    } else {
                        item
                    }
                }

            } else {

                listaActual + libro.copy(cantidad = 1)
            }
        }

        _mensajeSnackbar.value = "Libro agregado al carrito"
    }

    fun eliminarItemCarrito(libroId: String) {

        _itemsCarrito.update { listaActual ->

            listaActual.filter { it.id != libroId }
        }
    }

    fun disminuirCantidadItemCarrito(libro: Libro) {

        _itemsCarrito.update { listaActual ->

            listaActual.map { item ->

                if (item.id == libro.id) {

                    val nuevaCantidad = if (item.cantidad > 1) item.cantidad -1 else 1

                    item.copy(cantidad = nuevaCantidad)

                } else {
                    item
                }
            }
        }
    }


    //Lista de deseados
    fun agregarItemListaDeseados(libro: Libro) {
        _itemsListaDeseados.update { listaActual ->

            val existe = listaActual.any { it.id == libro.id }

            if (!existe) {
                val libroActualizado = libro.copy(estaEnListaDeseados = true)

                listaActual + libroActualizado

            } else {
                listaActual
            }
        }

        _mensajeSnackbar.value = "Libro agregado a lista de deseados"
    }

    fun eliminarItemListaDeseados(libroId: String) {

        _itemsListaDeseados.update { listaActual ->

            listaActual.filter { it.id != libroId }
        }

        _mensajeSnackbar.value = "Libro eliminado de lista deseados"
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
            themePreferences.saveDarkMode(!currentValue)
        }
    }

}