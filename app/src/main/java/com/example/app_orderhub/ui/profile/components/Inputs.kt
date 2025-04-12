package com.example.app_orderhub.ui.profile.components

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.app_orderhub.domain.model.Client
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun Inputs(
    modifier: Modifier = Modifier,
    client: Client
) {
    val context = LocalContext.current

    // Use remember com a chave do cliente para reinicializar quando o cliente mudar
    var name by remember(client) { mutableStateOf(client.nomePessoa) }
    var phoneNumer by remember(client) { mutableStateOf(client.numeroTelefone) }
    var dateOfBirth by remember(client) { mutableStateOf(client.dataNascimento) }
    var gender by remember(client) { mutableStateOf(client.genero) }

    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val datePickerDialog = remember { mutableStateOf<DatePickerDialog?>(null) }

    // Sempre que dateOfBirth mudar, atualizamos o dialog se ele existir
    LaunchedEffect(dateOfBirth) {
        datePickerDialog.value?.updateDate(
            dateOfBirth.year,
            dateOfBirth.monthValue - 1,
            dateOfBirth.dayOfMonth
        )
    }

    // Função para criar/show o dialog
    fun showDatePicker() {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                dateOfBirth = LocalDate.of(year, month + 1, dayOfMonth)
            },
            dateOfBirth.year,
            dateOfBirth.monthValue - 1,
            dateOfBirth.dayOfMonth
        ).also { dialog ->
            datePickerDialog.value = dialog
            dialog.show()
        }
    }

    Column(modifier = modifier.padding(12.dp)) {
        CustomTextFild(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .fillMaxWidth(),
            valueOf = name,
            labelText = "Nome:",
            changeValue = { name = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextFild(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .fillMaxWidth(),
            valueOf = phoneNumer,
            labelText = "Número de Telefone:",
            changeValue = { phoneNumer = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Campo de Data com borda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                value = dateOfBirth.format(dateFormatter),
                onValueChange = {},
                label = { Text("Data de Nascimento:", color = Color.Black) },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = ::showDatePicker) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Selecionar Data",
                            tint = Color.Black
                        )
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        ExposedGenderDropdown(
            selectedOption = gender,
            onOptionSelected = { gender = it }
        )
    }
}

@Composable
fun ExposedGenderDropdown(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val options = listOf("Masculino", "Feminino")
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text("Gênero:", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            trailingIcon = {
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "Abrir opções",
                    modifier = Modifier.clickable { expanded = true },
                    tint = Color.Black
                )
            },
            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(4.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = Color.Black,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier.background(Color.White)
                )
            }
        }
    }
}

@Composable
fun CustomTextFild(
    modifier: Modifier = Modifier,
    valueOf: String,
    changeValue: (String) -> Unit,
    labelText: String,
) {
    TextField(
        value = valueOf,
        label = {
            Text(text = labelText, color = Color.Black)
        },
        textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        onValueChange = {
            changeValue(it)
        }
    )
}
