package com.example.zennapp.ui.theme.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
fun FormattedDateTimeText(dateTimeString: String, fontSize: TextUnit = 18.sp, color: Color = Color.Black) {
    val formattedText = remember(dateTimeString) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatDateTime(dateTimeString, ZoneId.systemDefault())
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
    Text(text = formattedText, fontSize = fontSize, color = color)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTimeString: String, zoneId: ZoneId): String {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val dateTime = LocalDateTime.parse(dateTimeString, formatter)
    val instant = dateTime.atZone(zoneId).toInstant()
    val now = Instant.now()

    val diffMinutes = ChronoUnit.MINUTES.between(instant, now)
    val diffHours = ChronoUnit.HOURS.between(instant, now)
    val diffDays = ChronoUnit.DAYS.between(instant, now)

    return when {
        diffMinutes < 60 -> "${diffMinutes}分前"
        diffHours < 24 -> "${diffHours}時間前"
        else -> "${diffDays}日前"
    }
}