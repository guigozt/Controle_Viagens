package com.example.controleviagens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.controleviagens.ui.theme.ControleViagensTheme

class Viagem(
    val data: String,
    val kmInicial: Double,
    val kmFinal: Double,
    val litros: Double,
    val combustivel: String,
    val valorCombustivel: Double,
    val pedagio: Double
) {

    fun calcularDistancia(): Double {
        return kmFinal - kmInicial
    }

    fun calcularCustoCombustivel(): Double {
        return litros * valorCombustivel
    }

    fun calcularCustoTotal(): Double {
        return calcularCustoCombustivel() + pedagio
    }
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ControleViagensTheme {
                ControleViagensApp()
            }
        }
    }
}

@Composable
fun ControleViagensApp() {

    // =========================
    // CAMPOS
    // =========================

    var data by remember {
        mutableStateOf("")
    }

    var kmInicial by remember {
        mutableStateOf("")
    }

    var kmFinal by remember {
        mutableStateOf("")
    }

    var litros by remember {
        mutableStateOf("")
    }

    var combustivel by remember {
        mutableStateOf("")
    }

    var valorCombustivel by remember {
        mutableStateOf("")
    }

    var pedagio by remember {
        mutableStateOf("")
    }

    // Lista de viagens
    val viagens = remember {
        mutableStateListOf<Viagem>()
    }

    // =========================
    // TOTAIS
    // =========================

    val totalKm = viagens.sumOf {
        it.calcularDistancia()
    }

    val totalLitros = viagens.sumOf {
        it.litros
    }

    val totalGasto = viagens.sumOf {
        it.calcularCustoTotal()
    }

    val mediaKmLitro =
        if (totalLitros > 0) {
            totalKm / totalLitros
        } else {
            0.0
        }

    // =========================
    // TELA
    // =========================

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme.colorScheme.background
                )
                .padding(innerPadding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // =========================
            // TÍTULO
            // =========================

            item {

                Text(
                    text = "Controle de Viagens",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Cadastre e acompanhe suas viagens",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                        .copy(alpha = 0.65f)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }

            // =========================
            // DATA
            // =========================

            item {

                OutlinedTextField(
                    value = data,
                    onValueChange = {
                        data = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Data")
                    },

                    placeholder = {
                        Text("Ex: 01/10/2026")
                    },

                    singleLine = true,

                    shape = RoundedCornerShape(12.dp)
                )
            }

            // =========================
            // KM INICIAL E FINAL
            // =========================

            item {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    OutlinedTextField(
                        value = kmInicial,
                        onValueChange = {
                            kmInicial = it
                        },

                        modifier = Modifier.weight(1f),

                        label = {
                            Text("Km inicial")
                        },

                        placeholder = {
                            Text("Ex: 1000")
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),

                        singleLine = true,

                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    OutlinedTextField(
                        value = kmFinal,
                        onValueChange = {
                            kmFinal = it
                        },

                        modifier = Modifier.weight(1f),

                        label = {
                            Text("Km final")
                        },

                        placeholder = {
                            Text("Ex: 1250")
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),

                        singleLine = true,

                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            // =========================
            // COMBUSTÍVEL
            // =========================

            item {

                Text(
                    text = "Combustível",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            item {

                OutlinedTextField(
                    value = combustivel,
                    onValueChange = {
                        combustivel = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Tipo de combustível")
                    },

                    placeholder = {
                        Text("Ex: Gasolina")
                    },

                    singleLine = true,

                    shape = RoundedCornerShape(12.dp)
                )
            }

            item {

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    OutlinedTextField(
                        value = litros,
                        onValueChange = {
                            litros = it
                        },

                        modifier = Modifier.weight(1f),

                        label = {
                            Text("Litros")
                        },

                        placeholder = {
                            Text("Ex: 25.5")
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),

                        singleLine = true,

                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    OutlinedTextField(
                        value = valorCombustivel,
                        onValueChange = {
                            valorCombustivel = it
                        },

                        modifier = Modifier.weight(1f),

                        label = {
                            Text("Valor do litro")
                        },

                        placeholder = {
                            Text("Ex: 6.20")
                        },

                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal
                        ),

                        singleLine = true,

                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            // =========================
            // PEDÁGIO
            // =========================

            item {

                OutlinedTextField(
                    value = pedagio,
                    onValueChange = {
                        pedagio = it
                    },

                    modifier = Modifier.fillMaxWidth(),

                    label = {
                        Text("Pedágio")
                    },

                    placeholder = {
                        Text("Ex: 15.00")
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),

                    singleLine = true,

                    shape = RoundedCornerShape(12.dp)
                )
            }

            // =========================
            // BOTÃO
            // =========================

            item {

                Button(
                    onClick = {

                        val viagem = Viagem(
                            data = data,
                            kmInicial = kmInicial.toDouble(),
                            kmFinal = kmFinal.toDouble(),
                            litros = litros.toDouble(),
                            combustivel = combustivel,
                            valorCombustivel = valorCombustivel.toDouble(),
                            pedagio = pedagio.toDouble()
                        )

                        viagens.add(viagem)

                        // Limpa os campos
                        data = ""
                        kmInicial = ""
                        kmFinal = ""
                        litros = ""
                        combustivel = ""
                        valorCombustivel = ""
                        pedagio = ""
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),

                    shape = RoundedCornerShape(12.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {

                    Text(
                        text = "Cadastrar viagem",
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // =========================
            // RESUMO
            // =========================

            if (viagens.isNotEmpty()) {

                item {

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "Resumo",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

                item {

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        shape = RoundedCornerShape(16.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Total de quilômetros",
                                color = MaterialTheme.colorScheme.onPrimary
                                    .copy(alpha = 0.8f)
                            )

                            Text(
                                text = "%.2f km".format(totalKm),
                                color = MaterialTheme.colorScheme.onPrimary,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                ResumoItem(
                                    titulo = "Total gasto",
                                    valor = "R$ %.2f".format(totalGasto)
                                )

                                ResumoItem(
                                    titulo = "Média",
                                    valor = "%.2f km/l".format(mediaKmLitro)
                                )
                            }
                        }
                    }
                }

                item {

                    Text(
                        text = "Viagens cadastradas",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // =========================
            // VIAGENS
            // =========================

            items(viagens) { viagem ->

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = viagem.data,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            "Combustível: ${viagem.combustivel}"
                        )

                        Text(
                            "Distância: %.2f km".format(
                                viagem.calcularDistancia()
                            )
                        )

                        Text(
                            "Litros: %.2f L".format(
                                viagem.litros
                            )
                        )

                        Text(
                            "Custo combustível: R$ %.2f".format(
                                viagem.calcularCustoCombustivel()
                            )
                        )

                        Text(
                            "Pedágio: R$ %.2f".format(
                                viagem.pedagio
                            )
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Divider()

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Custo total",
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "R$ %.2f".format(
                                    viagem.calcularCustoTotal()
                                ),
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ResumoItem(
    titulo: String,
    valor: String
) {

    Column {

        Text(
            text = titulo,
            color = MaterialTheme.colorScheme.onPrimary
                .copy(alpha = 0.75f)
        )

        Text(
            text = valor,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}
