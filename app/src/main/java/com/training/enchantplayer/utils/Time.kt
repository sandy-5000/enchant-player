package com.training.enchantplayer.utils

import android.annotation.SuppressLint

data class Time(val seconds: Int) {
    @SuppressLint("DefaultLocale")
    override fun toString(): String {
        val seconds = this.seconds % 60
        val minutes: Int = (this.seconds / 60) % 60
        val hours = (this.seconds / 3600)

        val formattedMinutes = String.format("%02d", minutes)
        val formattedSeconds = String.format("%02d", seconds)

        if (hours > 0) {
            return "$hours:$formattedMinutes:$formattedSeconds"
        }
        return "$formattedMinutes:$formattedSeconds"
    }
}
