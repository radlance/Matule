package com.radlance.matule.presentation.profile

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.WindowManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radlance.matule.R
import com.radlance.matule.ui.theme.MatuleTheme

@Composable
fun FullScreenBarcode(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()


    BackHandler {
        onBackPressed()
    }

    LaunchedEffect(Unit) {
        (context as? Activity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        changeBrightness(context, true)
    }

    DisposableEffect(Unit) {
        onDispose {
            resetSettings(context)
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(dimensionResource(R.dimen.main_top_padding)))
        FullScreenBarcodeHeader(
            onBackPressed = {
                onBackPressed()
                resetSettings(context)
            }
        )
        Spacer(Modifier.height(40.dp))
        Image(
            painter = painterResource(R.drawable.barcode_example),
            contentDescription = "barcode_example",
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.height(140.dp))
    }
}

private fun changeBrightness(context: Context, bright: Boolean) {
    (context as? Activity)?.window?.attributes?.let { layoutParams ->
        layoutParams.screenBrightness = if (bright) {
            0.8f
        } else {
            WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE
        }
        context.window.attributes = layoutParams
    }
}

private fun resetSettings(context: Context) {
    (context as? Activity)?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    changeBrightness(context, false)
}


@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun FullScreenBarcodePreview() {
    MatuleTheme {
        FullScreenBarcode({})
    }
}