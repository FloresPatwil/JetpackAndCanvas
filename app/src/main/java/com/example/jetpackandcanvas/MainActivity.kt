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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun IconBox(
    xPos: Float,
    yPos: Float,
    emergencyImageId: Int
) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(start = xPos.dp, top = yPos.dp)
            .size(70.dp)
            .clickable {
                showDialog = true
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Ícono",
            modifier = Modifier.padding(3.dp)
        )

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Box(
                    modifier = Modifier
                        .size(300.dp)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = emergencyImageId),
                        contentDescription = "Imagen de un artista famoso",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var circlePosition by remember { mutableStateOf(Offset(200f, 600f)) }
    val circleRadius = 70f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        IconBox(xPos = 70f, yPos = 160f, emergencyImageId = R.drawable.noalisa)
        IconBox(xPos = 175f, yPos = 160f, emergencyImageId = R.drawable.elcaminante)
        IconBox(xPos = 260f, yPos = 160f, emergencyImageId = R.drawable.elcolumpio)
        IconBox(xPos = 70f, yPos = 270f, emergencyImageId = R.drawable.elalmuerzodelosremeros)
        IconBox(xPos = 70f, yPos = 380f, emergencyImageId = R.drawable.elcaminante)
        IconBox(xPos = 175f, yPos = 490f, emergencyImageId = R.drawable.themariet)
        IconBox(xPos = 175f, yPos = 600f, emergencyImageId = R.drawable.noalisa)
        IconBox(xPos = 175f, yPos = 710f, emergencyImageId = R.drawable.elalmuerzodelosremeros)
        IconBox(xPos = 260f, yPos = 710f, emergencyImageId = R.drawable.elcolumpio)

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
    } // Llamar a la funcion IconBox con parametros de x,y, e imagen que mostrara
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}
