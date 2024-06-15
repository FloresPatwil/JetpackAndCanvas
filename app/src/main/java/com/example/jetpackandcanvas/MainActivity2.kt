package com.example.jetpackandcanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp2()
        }
    }
}

@Composable
fun MyApp2() {
    var showDialog by remember { mutableStateOf(false) }
    var circlePosition by remember { mutableStateOf(Offset(200f, 600f)) }
    val circleRadius = 70f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        // Ícono que lleva a otra pantalla o pantalla emergente
        Box(
            modifier = Modifier
                .padding(60.dp)
                .size(70.dp)
                .clickable {
                    showDialog = true
                },
            contentAlignment = Alignment.CenterEnd
        ) {
            // Contenido del ícono
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Ícono",
                modifier = Modifier.padding(3.dp)
            )
        }

        // Botón que cambia la posición del círculo verde
        Button(
            onClick = {
                // Obtener el tamaño de la pantalla
                val screenWidth = 1080f
                val screenHeight = 1920f

                // Calcular una nueva posición aleatoria para el círculo
                val newX = (Math.random() * (screenWidth - 2 * circleRadius)).toFloat() + circleRadius
                val newY = (Math.random() * (screenHeight - 2 * circleRadius)).toFloat() + circleRadius

                circlePosition = Offset(newX, newY)
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(60.dp)
        ) {
            Text(text = "Button")
        }

        // Canvas que dibuja el rectángulo vacío con marcos negros y el círculo verde
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            val rectPadding = 16.dp.toPx()
            val rectWidth = size.width - 2 * rectPadding
            val rectHeight = size.height - 2 * rectPadding
            drawRect(
                color = Color.Black,
                topLeft = Offset(rectPadding, rectPadding),
                size = androidx.compose.ui.geometry.Size(rectWidth, rectHeight),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5f) // Grosor del borde
            )
            drawCircle(
                color = Color.Green,
                radius = circleRadius,
                center = circlePosition
            )
        }
    }

    // Pantalla emergente que muestra la imagen de un artista famoso
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.noalisa),
                    contentDescription = "Imagen de un artista famoso",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp2() {
    MyApp2()
}
