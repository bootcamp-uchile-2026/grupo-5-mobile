package cl.bootcamp.leeconnos.viewmodel

import androidx.lifecycle.ViewModel
import cl.bootcamp.leeconnos.data.modelos.Libro
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _cantidadItemsCarrito = MutableStateFlow(0)
    val cantidadItemsCarrito: StateFlow<Int> = _cantidadItemsCarrito.asStateFlow()

    private val _itemsListaDeseados = MutableStateFlow<List<Libro>>(emptyList())
    val itemsListaDeseados = _itemsListaDeseados.asStateFlow()

    //Carrito
    fun agregarItemCarrito() {
        _cantidadItemsCarrito.value += 1
    }

    fun eliminarItemCarrito() {
        _cantidadItemsCarrito.value -= 1
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
    }

    fun eliminarItemListaDeseados(libroId: String) {

        _itemsListaDeseados.update { listaActual ->

            listaActual.filter { it.id != libroId }
        }
    }

}