package com.example.zennapp.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.zennapp.model.ZennArticle
import com.example.zennapp.ui.theme.PaleLightBlue
import com.example.zennapp.ui.theme.components.ArticleCard

data class DropdownMenuItemData(
    val label: String,
    val onClick: () -> Unit
)

@Composable
fun ArticlesScreen(
    articles: ZennArticle,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    menuItems: List<DropdownMenuItemData>
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Surface(
        color = PaleLightBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Tech",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Box {
                    IconButton(
                        onClick = {
                            isMenuExpanded = true
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp)),
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        menuItems.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item.label) },
                                onClick = {
                                    isMenuExpanded = false
                                    item.onClick()
                                }
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(items = articles.articles, key = { article -> article.id }) { article ->
                    ArticleCard(
                        article,
                        modifier = modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}