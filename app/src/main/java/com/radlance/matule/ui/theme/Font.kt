package com.radlance.matule.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.radlance.matule.R

val ralewayFamily = FontFamily(
    Font(R.font.raleway_light),
    Font(R.font.raleway_bold, weight = FontWeight.Bold),
    Font(R.font.raleway_black, weight = FontWeight.Black),
    Font(R.font.raleway_semi_bold, weight = FontWeight.SemiBold),
    Font(R.font.raleway_regular, weight = FontWeight.Normal),
    Font(R.font.raleway_medium, weight = FontWeight.Medium),
)

val poppinsFamily = FontFamily(
    Font(R.font.poppins_regular, weight = FontWeight.Normal),
    Font(R.font.poppins_medium, weight = FontWeight.Medium)
)