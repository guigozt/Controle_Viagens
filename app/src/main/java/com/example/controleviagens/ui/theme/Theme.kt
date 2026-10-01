package com.example.controleviagens.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cores principais
private val Azul = Color(0xFF1976D2)
private val AzulEscuro = Color(0xFF0D47A1)
private val AzulClaro = Color(0xFF42A5F5)

private val FundoClaro = Color(0xFFF5F7FA)
private val Branco = Color(0xFFFFFFFF)

private val FundoEscuro = Color(0xFF121212)
private val SuperficieEscura = Color(0xFF1E1E1E)

// Tema escuro
private val DarkColorScheme = darkColorScheme(
    primary = AzulClaro,
    secondary = Color(0xFF90CAF9),
    tertiary = Color(0xFF81C784),

    background = FundoEscuro,
    surface = SuperficieEscura
)

// Tema claro
private val LightColorScheme = lightColorScheme(
    primary = Azul,
    secondary = AzulClaro,
    tertiary = Color(0xFF43A047),

    background = FundoClaro,
    surface = Branco,

    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF202124),
    onSurface = Color(0xFF202124)
)

@Composable
fun ControleViagensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme =
        if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
