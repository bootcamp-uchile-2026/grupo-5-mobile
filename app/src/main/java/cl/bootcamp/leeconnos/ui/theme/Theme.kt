package cl.bootcamp.leeconnos.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,

)

private val LightColorScheme = lightColorScheme(
    // Colores principales de la marca
    primary = BrandPrimary,
    primaryContainer = BrandPrimaryLight,

    secondary = BrandSecondary,
    secondaryContainer = BrandSecondaryLight,

    tertiary = BrandTertiary,
    tertiaryContainer = BrandTertiaryLight,

    // Fondos estructurales
    background = BgPrimaryWhite,
    surface = BgPrimaryWhite,

    // Estados de error
    error = ErrorPrimary,
    errorContainer = ErrorLightBg,

    // Colores "On" (Texto o iconos que van por encima de los colores anteriores)
    onPrimary = White,
    onSecondary = BrandNeutral, // Texto oscuro sobre tu fondo amarillo
    onBackground = BrandNeutral, // Tu gris oscuro (#303030) para el texto general
    onSurface = BrandNeutral
)

@Composable
fun LeeConNosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}