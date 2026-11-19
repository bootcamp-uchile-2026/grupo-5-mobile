package cl.bootcamp.leeconnos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.api.dto.RegistroRequestDto
import cl.bootcamp.leeconnos.data.repositorio.AuthRepository
import cl.bootcamp.leeconnos.data.repositorio.AuthResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    //Campos compartidos
    val email: String = "",
    val contrasena: String = "",

    //Campos Registro
    val nombre: String = "",
    val apellido: String = "",
    val repetirContrasena: String = "",
    val aceptoTerminos: Boolean = false,
    val recibirNovedades: Boolean = false,

    //Estados
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loginExitoso: Boolean = false
) {

    val esLoginValido: Boolean
        get() = email.isNotBlank() && contrasena.isNotBlank()

    val esRegistroValido: Boolean
        get() = nombre.isNotBlank() &&
                apellido.isNotBlank() &&
                email.isNotBlank() &&
                contrasena.isNotBlank() &&
                contrasena == repetirContrasena &&
                aceptoTerminos

    val mensajeErrorRegistro: String?
        get() = when {

            esRegistroValido -> null

            nombre.isBlank() && apellido.isBlank() && email.isBlank() && contrasena.isBlank() -> null

            nombre.isBlank() -> "Por favor ingresa tu nombre"
            apellido.isBlank() -> "Por favor ingresa tu apellido"
            email.isBlank() -> "Por favor ingresa tu correo electrónico"
            contrasena.isBlank() -> "Por favor ingresa una contraseña"
            contrasena != repetirContrasena -> "Las contraseñas no coinciden"
            !aceptoTerminos -> "Debes aceptar los Términos y Condiciones"
            else -> null //Sin errores
        }
}

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    //Actualizar campos de formulario
    fun onEmailChange(nuevoEmail: String) {
        _uiState.update { it.copy(email = nuevoEmail, errorMessage = null) }
    }

    fun onContrasenaChange(nuevaContrasena: String) {
        _uiState.update { it.copy(contrasena = nuevaContrasena, errorMessage = null) }
    }

    fun onNombreChange(nuevoNombre: String) {
        _uiState.update { it.copy(nombre = nuevoNombre) }
    }

    fun onApellidoChange(nuevoApellido: String) {
        _uiState.update { it.copy(apellido = nuevoApellido) }
    }

    fun onRepetirContrasenaChange(nuevaContrasena: String) {
        _uiState.update { it.copy(repetirContrasena = nuevaContrasena) }
    }

    fun onAceptoTerminosChange(acepta: Boolean) {
        _uiState.update { it.copy(aceptoTerminos = acepta) }
    }

    fun onRecibirNovedadesChange(recibe: Boolean) {
        _uiState.update { it.copy(recibirNovedades = recibe) }
    }

    fun limpiarError() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    fun cambiarModoAutenticacion() {
        _uiState.update { state ->
            state.copy(
                //email se mantiene por UX
                contrasena = "",
                repetirContrasena = "",
                nombre = "",
                apellido = "",
                errorMessage = null,
                isLoading = false
            )
        }
    }

    fun login() {

        val email = _uiState.value.email
        val contrasena = _uiState.value.contrasena

        if (email.isBlank() || contrasena.isBlank()) return

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {

            when (val result = authRepository.login(email, contrasena)) {

                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false, loginExitoso = true) }
                }

                is AuthResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.mensaje) }
                }
            }
        }
    }

    fun registrar() {

        val state = _uiState.value

        if (!state.esRegistroValido) {
            _uiState.update { it.copy(errorMessage = "Por favor completa todos los campos correctamente.") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {

            val registroDto = RegistroRequestDto(
                nombre = state.nombre,
                apellido = state.apellido,
                email = state.email,
                contrasena = state.contrasena,
                recibirNovedades = state.recibirNovedades
            )

            when (val result = authRepository.registrar(registroDto)) {

                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false, loginExitoso = true) }
                }

                is AuthResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, errorMessage = result.mensaje) }
                }
            }
        }
    }
}