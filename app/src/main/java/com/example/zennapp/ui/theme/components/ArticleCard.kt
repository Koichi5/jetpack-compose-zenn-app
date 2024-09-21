package com.example.zennapp.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.zennapp.R
import com.example.zennapp.model.Article
import com.example.zennapp.ui.theme.Grey

@Composable
fun ArticleCard(article: Article, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(vertical = 16.dp)
    ) {
        ArticleIcon(emoji = article.emoji)
        Spacer(modifier = Modifier.width(16.dp))
        ArticleInfo(article = article)
    }
}

@Composable
fun ArticleInfo(article: Article) {
    Column {
        Text(article.title, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val userAvatarUrl = article.user.avatarSmallUrl
            AsyncImage(
                model = userAvatarUrl,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(25.dp),
                placeholder = painterResource(id = R.drawable.ic_connection_error)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(article.user.name, fontSize = 15.sp)
            Spacer(modifier = Modifier.width(8.dp))
            FormattedDateTimeText(article.publishedAt, fontSize = 15.sp, color = Grey)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = "Like",
                tint = Grey,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(article.likedCount.toString(), fontSize = 15.sp, color = Grey)
        }
    }
}

@Composable
fun ArticleIcon(emoji: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .size(80.dp)
    ) {
        Text(
            emoji,
            style = TextStyle(fontSize = 30.sp)
        )
    }
}