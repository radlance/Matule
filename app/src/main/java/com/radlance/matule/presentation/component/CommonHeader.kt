package com.radlance.matule.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme
import com.radlance.matule.ui.theme.fillRedColor
import com.radlance.matule.ui.theme.ralewayFamily
import com.radlance.matule.ui.vector.BagIcon
import com.radlance.matule.ui.vector.Highlight05
import com.radlance.matule.ui.vector.MenuIcon

@Composable
fun CommonHeader(
    modifier: Modifier = Modifier,
    startContent: @Composable () -> Unit,
    middleContent: @Composable () -> Unit,
    endContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(Modifier.align(Alignment.CenterStart)) {
            startContent()
        }

        Box((Modifier.align(Alignment.Center))) {
            middleContent()
        }

        Box((Modifier.align(Alignment.CenterEnd))) {
            endContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommonHeaderPreview() {
    MatuleTheme {
        CommonHeader(
            modifier = Modifier.fillMaxWidth(),
            startContent = {
                IconButton(onClick = {}) {
                    Image(
                        imageVector = MenuIcon(MaterialTheme.colorScheme.onSurface),
                        contentDescription = "home_highlight_1"
                    )
                }
            },
            middleContent = {
                Icon(
                    imageVector = Highlight05,
                    contentDescription = "Highlight05",
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.offset(x = (-12).dp, y = (-7).dp)
                )
                Text(
                    text = stringResource(R.string.main_screen),
                    fontSize = 32.sp,
                    fontFamily = ralewayFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 38.sp
                )
            },
            endContent = {
                BadgedBox(
                    badge = {
                        Badge(
                            modifier = Modifier.offset(x = (-2).dp, y = 5.dp),
                            contentColor = fillRedColor,
                            containerColor = fillRedColor
                        )
                    }
                ) {
                    IconButton(
                        onClick = {}, modifier = Modifier
                            .clip(CircleShape)
                            .size(44.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = BagIcon(MaterialTheme.colorScheme.background),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "home_highlight_1"
                        )
                    }
                }
            }
        )
    }
}