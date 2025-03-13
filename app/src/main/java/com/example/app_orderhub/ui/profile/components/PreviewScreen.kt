import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip


@Composable
fun optionProfile() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        OptionItem(
            icon = painterResource(id = android.R.drawable.ic_dialog_info),
            title = "Ajuda e suporte"
        )
        Spacer(modifier = Modifier.height(8.dp))
        OptionItem(
            icon = painterResource(id = android.R.drawable.ic_dialog_alert),
            title = "Sobre o aplicativo"
        )
    }
}

@Composable
fun OptionItem(icon: Painter, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            color = Color(0xFFFFFFFF),
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

    }
}

@Preview
@Composable
fun PreviewOptionProfile() {
    optionProfile()
}

@Preview(showBackground = true)
@Composable
fun PreviewOptionItem() {
    OptionItem(
        icon = painterResource(id = android.R.drawable.ic_dialog_info),
        title = "Ajuda e suporte"
    )
}
