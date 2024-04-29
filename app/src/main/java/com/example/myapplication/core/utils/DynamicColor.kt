package com.example.myapplication.core.utils

import kotlin.random.Random


class DynamicColor {

    companion object {
        fun getRandomColor(): String {
            val colors = arrayOf("#a3b5e2", "#bac7e8", "#8da5e1", "#a3d4e2", "#cae5e8", "#8faead", "#b5cbcf", "#0a4260")

            val randomIndex = Random.nextInt(colors.size)
            return colors[randomIndex]
        }
    }

}