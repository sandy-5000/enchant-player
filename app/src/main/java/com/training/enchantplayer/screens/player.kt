package com.training.enchantplayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.PlaylistAdd
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.outlined.Equalizer
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.PauseCircleOutline
import androidx.compose.material.icons.outlined.Pending
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material.icons.outlined.PlayCircleFilled
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material.icons.outlined.RepeatOne
import androidx.compose.material.icons.outlined.Shuffle
import androidx.compose.material.icons.outlined.SkipNext
import androidx.compose.material.icons.outlined.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.training.enchantplayer.R
import com.training.enchantplayer.ui.theme.EnchantPlayerTheme
import com.training.enchantplayer.ui.theme.appBackground
import com.training.enchantplayer.ui.theme.appGreen
import com.training.enchantplayer.utils.types.NavIcon
import com.training.enchantplayer.utils.types.Time


@Composable
fun Player(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxHeight(0.5f),
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(25.dp))
                    .fillMaxWidth()
                    .background(color = appGreen),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayCircleFilled,
                    contentDescription = "Play",
                    modifier = Modifier.size(240.dp),
                    tint = appBackground,
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            ) {
                SongDetails()
                SongOptions()
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    ProgressBar()
                    PlayOptions()
                }
            }
        }
    }
}

@Composable
fun SongImage() {
    Image(
        painter = painterResource(id = R.drawable.player_background),
        contentDescription = "Music Player",
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(25.dp))
            .fillMaxWidth()
            .graphicsLayer(alpha = 0.7f),
    )
}

@Composable
fun SongDetails() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Star Boy",
                color = Color.White,
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "The Weekend",
                color = Color.White,
                fontSize = 12.sp,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "My Songs",
                color = Color.White,
                fontSize = 12.sp,
            )
        }
    }
}

@Composable
fun SongOptions() {
    val optionsGap = 8.dp
    val shuffleOptionsGap = 4.dp

    val startIcons = arrayOf(
        NavIcon(
            icon = Icons.Outlined.FavoriteBorder,
            description = "Favorite",
        ),
        NavIcon(
            icon = Icons.Outlined.ErrorOutline,
            description = "About",
        ),
        NavIcon(
            icon = Icons.AutoMirrored.Outlined.PlaylistAdd,
            description = "Add to queue",
        ),
        NavIcon(
            icon = Icons.Outlined.Pending,
            description = "Options",
        ),
    )

    val endIcons = arrayOf(
        arrayOf(
            NavIcon(
                icon = Icons.Outlined.RepeatOne,
                description = "Repeat One",
            ),
            NavIcon(
                icon = Icons.Outlined.Repeat,
                description = "Repeat All",
            ),
        ),
        arrayOf(
            NavIcon(
                icon = Icons.Outlined.Shuffle,
                description = "Shuffle",
            ),
        ),
    )

    var endIconsState by remember {
        mutableStateOf(IntArray(endIcons.size) { 0 })
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp),
        ) {
            startIcons.forEach { item ->
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(24.dp)
                        .width(optionsGap + 24.dp + optionsGap)
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.description,
                        modifier = Modifier
                            .padding(start = optionsGap, end = optionsGap)
                            .fillMaxHeight(),
                        tint = Color.White,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            endIconsState.forEachIndexed { index, itemIndex ->
                IconButton(
                    onClick = {
                        endIconsState[index] = (endIconsState[index] + 1) % endIcons[index].size
                    },
                    modifier = Modifier
                        .height(24.dp)
                        .width(shuffleOptionsGap + 24.dp + shuffleOptionsGap)
                ) {
                    Icon(
                        imageVector = endIcons[index][itemIndex].icon,
                        contentDescription = endIcons[index][itemIndex].description,
                        modifier = Modifier
                            .padding(start = shuffleOptionsGap, end = shuffleOptionsGap)
                            .fillMaxHeight(),
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun ProgressBar() {
    val total = 195
    var current by remember {
        mutableIntStateOf(107)
    }
    var progress by remember {
        mutableFloatStateOf(current * 1f / total)
    }
    Row(
        modifier = Modifier
            .height(40.dp)
            .padding(top = 12.dp),
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            val width = maxWidth - 40.dp
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .padding(end = 4.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = Time(current).toString(),
                    color = Color.White,
                    fontSize = 13.sp,
                )
            }
            Column(
                modifier = Modifier
                    .width(width)
                    .padding(start = 40.dp)
                    .fillMaxHeight()
                    .padding(top = 2.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Slider(
                    value = progress,
                    onValueChange = {
                        progress = it
                        current = (it * total).toInt()
                    },
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = width + 4.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = Time(total).toString(),
                    color = Color.White,
                    fontSize = 13.sp,
                )
            }
        }
    }
}

@Composable
fun PlayOptions() {
    var isPlaying by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp)
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.VolumeUp,
                contentDescription = "Volume",
                modifier = Modifier.size(20.dp),
                tint = Color.White,
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.SkipPrevious,
                contentDescription = "SkipPrevious",
                modifier = Modifier.size(40.dp),
                tint = Color.White,
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    isPlaying = !isPlaying
                },
                modifier = Modifier.size(52.dp),
            ) {
                if (isPlaying) {
                    Icon(
                        imageVector = Icons.Outlined.PauseCircleOutline,
                        contentDescription = "Pause",
                        modifier = Modifier.size(52.dp),
                        tint = Color.White,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.PlayCircle,
                        contentDescription = "Play",
                        modifier = Modifier.size(52.dp),
                        tint = Color.White,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.SkipNext,
                contentDescription = "SkipNext",
                modifier = Modifier.size(40.dp),
                tint = Color.White,
            )
        }
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Equalizer,
                contentDescription = "Equalizer",
                modifier = Modifier.size(20.dp),
                tint = Color.White,
            )
        }
    }
}

@Preview
@Composable
fun PlayerPreview() {
    EnchantPlayerTheme {
        Player(modifier = Modifier)
    }
}
