package co.edu.unab.EliasCruz.myapplication.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.unab.EliasCruz.myapplication.ui.theme.ExploraColombiaAppTheme

@Composable
fun AddPlaceScreen(
    onBackClick: () -> Unit = {},
    onPublishClick: () -> Unit = {}
) {
    // Variables de estado para los campos de texto
    var placeName by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Colores de tu paleta base
    val primaryOrange = Color(0xFFE45D25)
    val lightGrayBg = Color(0xFFF8F9FE)
    val inputBg = Color(0xFFE5E5EA)
    val darkRedText = Color(0xFFA02B13) // Color oscuro para el título "Add Place"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = lightGrayBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            // 1. HEADER (Botón de atrás y Título)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.offset(x = (-12).dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver",
                        tint = darkRedText
                    )
                }
                Text(
                    text = "Add Place",
                    color = darkRedText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.offset(x = (-8).dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. BANNER DE GRADIENTE
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(darkRedText, primaryOrange, Color(0xFFFF8A65))
                        )
                    )
                    .padding(horizontal = 24.dp, vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Comparte tu\ndescubrimiento",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ayuda a otros viajeros a encontrar los tesoros escondidos de nuestra tierra.",
                        color = Color.White,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // 3. CAMPOS DE TEXTO
            AddPlaceField(
                label = "NOMBRE DEL LUGAR",
                value = placeName,
                onValueChange = { placeName = it },
                placeholder = "Ej: Cascada del Fin del Mundo",
                inputBg = inputBg
            )

            Spacer(modifier = Modifier.height(20.dp))

            AddPlaceField(
                label = "DEPARTAMENTO",
                value = department,
                onValueChange = { department = it },
                placeholder = "Ej: Putumayo",
                inputBg = inputBg
            )

            Spacer(modifier = Modifier.height(20.dp))

            AddPlaceField(
                label = "CIUDAD",
                value = city,
                onValueChange = { city = it },
                placeholder = "Ej: Mocoa",
                inputBg = inputBg
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campo de Descripción (Más alto/Multilínea)
            Text(
                text = "DESCRIPCIÓN",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // Altura personalizada para la descripción
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = { Text("Cuéntanos por qué este lugar es especial...", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = inputBg,
                    unfocusedContainerColor = inputBg,
                    disabledContainerColor = inputBg,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // 4. BOTÓN PUBLICAR
            Button(
                onClick = onPublishClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(primaryOrange, Color(0xFFFF8A65))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Publicar", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Función reutilizable para los campos de texto estándar
@Composable
fun AddPlaceField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    inputBg: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(16.dp)),
            placeholder = { Text(placeholder, color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = inputBg,
                unfocusedContainerColor = inputBg,
                disabledContainerColor = inputBg,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddPlaceScreenPreview() {
    ExploraColombiaAppTheme {
        AddPlaceScreen()
    }
}