package com.jetpack.reader.app.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.size.Size
import com.jetpack.reader.app.R
import com.jetpack.reader.app.components.ReaderLogo
import com.jetpack.reader.app.navigation.ReaderScreens
import kotlinx.coroutines.delay

@Composable
fun ReaderSplashScreen(navController: NavController) {
    val scale= remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 0.7f, animationSpec = tween(
            durationMillis = 800, easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
        ))
        delay(2000L)
        navController.navigate(ReaderScreens.LoginScreen.name)

    }
    Surface(modifier = Modifier
        .padding(15.dp).scale(scale.value)
        .size(400.dp)
    ){
        ReaderLogo()
    }
}

