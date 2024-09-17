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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.QueueMusic
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.training.enchantplayer.screens.Player
import com.training.enchantplayer.ui.theme.EnchantPlayerTheme


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

    EnchantPlayerTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                Row(
                    modifier = Modifier
                        .height(64.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    NavigationBarButton(
                        weight = 1f / 5,
                        tab = 0,
                        icon = Icons.AutoMirrored.Outlined.QueueMusic,
                        description = "QueueMusic",
                        onClick = { tab -> onClicks(tab) },
                        selected = selectedTab == 0
                    )
                    NavigationBarButton(
                        weight = 1f / 4,
                        tab = 1,
                        icon = Icons.Outlined.PlayCircleFilled,
                        description = "Play",
                        onClick = { tab -> onClicks(tab) },
                        selected = selectedTab == 1
                    )
                    NavigationBarButton(
                        weight = 1f / 3,
                        tab = 2,
                        icon = Icons.Filled.Folder,
                        description = "File System",
                        onClick = { tab -> onClicks(tab) },
                        selected = selectedTab == 2
                    )
                    NavigationBarButton(
                        weight = 1f / 2,
                        tab = 3,
                        icon = Icons.Outlined.DownloadForOffline,
                        description = "YouTube",
                        onClick = { tab -> onClicks(tab) },
                        selected = selectedTab == 3
                    )
                    NavigationBarButton(
                        weight = 1f / 1,
                        tab = 4,
                        icon = Icons.Filled.MoreVert,
                        description = "Options",
                        onClick = { tab -> onClicks(tab) },
                        selected = selectedTab == 4
                    )
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
        Color.Cyan
    } else {
        Color.Transparent
    }
    val tintColor = if (selected) {
        Color.Black
    } else {
        Color.White
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(weight)
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
