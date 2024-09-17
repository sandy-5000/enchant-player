package com.training.enchantplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.QueueMusic
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DownloadForOffline
import androidx.compose.material.icons.outlined.PlayCircleFilled
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.training.enchantplayer.screens.Player
import com.training.enchantplayer.ui.theme.EnchantPlayerTheme
import com.training.enchantplayer.ui.theme.appGreen
import com.training.enchantplayer.utils.types.NavIcon


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EnchantPlayer()
        }
    }
}

@Composable
fun EnchantPlayer() {
    var selectedTab by remember {
        mutableIntStateOf(1)
    }

    fun onClicks(k: Int) {
        selectedTab = k
    }

    val navIcons = arrayOf(
        NavIcon(
            icon = Icons.AutoMirrored.Outlined.QueueMusic,
            description = "Queue",
        ),
        NavIcon(
            icon = Icons.Outlined.PlayCircleFilled,
            description = "Play",
        ),
        NavIcon(
            icon = Icons.Filled.Folder,
            description = "File System",
        ),
        NavIcon(
            icon = Icons.Outlined.DownloadForOffline,
            description = "YouTube",
        ),
        NavIcon(
            icon = Icons.Filled.Person,
            description = "Profile",
        ),
        NavIcon(
            icon = Icons.Filled.LibraryMusic,
            description = "Library",
        ),
        NavIcon(
            icon = Icons.Filled.MoreVert,
            description = "Options",
        ),
    )

    EnchantPlayerTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Row(
                    modifier = Modifier
                        .height(48.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    navIcons.forEachIndexed { index, item ->
                        NavigationBarButton(
                            weight = 1f / (navIcons.size - index),
                            tab = index,
                            icon = item.icon,
                            description = item.description,
                            onClick = { tab -> onClicks(tab) },
                            selected = selectedTab == index
                        )
                    }
                }
            }
        ) { innerPadding ->
            Player(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun NavigationBarButton(
    weight: Float,
    tab: Int,
    icon: ImageVector,
    description: String,
    onClick: (Int) -> Unit,
    selected: Boolean,
) {
    val bgColor = if (selected) {
        appGreen
    } else {
        Color.Transparent
    }
    val tintColor = if (selected) {
        Color.Black
    } else {
        Color.White
    }
    val clipSize = 8.dp

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(weight)
            .clip(RoundedCornerShape(topStart = clipSize, topEnd = clipSize))
            .background(bgColor),
    ) {
        IconButton(
            onClick = { onClick(tab) },
            modifier = Modifier.fillMaxSize(),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = description,
                tint = tintColor,
            )
        }
    }
}

@Preview
@Composable
fun EnchantPlayerPreview() {
    EnchantPlayer()
}
