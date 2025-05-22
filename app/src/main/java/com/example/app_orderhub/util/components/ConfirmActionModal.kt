package com.example.app_orderhub.util.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.app_orderhub.R


@Composable
fun ConfirmActionModal(
    type: String,
    title: String,
    icon: Painter,
    message: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    val isConfirmType = type == "Confirm"
    val primaryButtonText = if (isConfirmType) "Ver Agendamentos" else "Cancelar"
    val primaryButtonColor = if (isConfirmType) Color(0xFF007BFF) else Color(0xFFFF3B30)
    val titleColor = if (isConfirmType) Color(0xFF1B873F) else Color.Black

    AlertDialog(
        onDismissRequest = onCancel,
        shape = RoundedCornerShape(8.dp),
        containerColor = Color.White,
        modifier = Modifier
            .padding(16.dp),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = onCancel,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 28.dp, y = (-28).dp)
                        .zIndex(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fechar",
                        Modifier.size(30.dp),
                        tint = Color.Black
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Image(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = titleColor
                    )
                }
            }
        },
        text = {
            Text(
                color = Color.Black,
                text = message,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        },
        confirmButton = {
            if (isConfirmType) {
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = primaryButtonText, color = Color.White)
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onCancel,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Voltar", color = Color.White)
                    }
                    Button(
                        onClick = onConfirm,
                        colors = ButtonDefaults.buttonColors(containerColor = primaryButtonColor),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = primaryButtonText, color = Color.White)
                    }
                }
            }
        }
    )
}


@Composable
@Preview
private fun PreviewConfirmActionModal() {
    ConfirmActionModal(
        type = "Confirm",
        title = "Confirmação do Agendamento",
        icon = painterResource(id = R.drawable.checked),
        message = "Agendamento Confirmado para o dia 20/10/2021 às 14:00",
        onConfirm = {},
        onCancel = {}
    )
}

@Composable
@Preview
private fun PreviewCancelActionModal() {
    ConfirmActionModal(
        type = "Cancel",
        title = "Cancelamento do Agendamento",
        icon = painterResource(id = R.drawable.canceled),
        message = "Tem certeza que deseja cancelar o agendamento na Barbearia NK para o dia 20/10/2021 às 14:00?",
        onConfirm = {},
        onCancel = {}
    )
}