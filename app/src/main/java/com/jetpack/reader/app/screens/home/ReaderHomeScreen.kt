package com.jetpack.reader.app.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.jetpack.reader.app.R
import com.jetpack.reader.app.navigation.ReaderScreens

@Composable
fun ReaderHomeScreen(navController: NavController) {
Scaffold(topBar = {
ReaderAppBar(title = "ReaderApp", navController = navController)
}, floatingActionButton = {}) {
    FABContent(){

    }
    Surface(modifier = Modifier.fillMaxSize()) {

    }
}
}
@Composable
fun ReaderAppBar(
    title:String,
    showProfile:Boolean=true,
    navController: NavController
){
    TopAppBar(title={
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (showProfile){
                         Icon(imageVector = Icons.Default.Favorite, contentDescription ="Profile",
                             modifier= Modifier
                                 .clip(RoundedCornerShape(12.dp))
                                 .scale(0.6f) )
                        }
                        Text(text = title,color= Color.Red.copy(0.7f),
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp) )
                    }
    }, actions = {
                 IconButton(onClick = {
                     FirebaseAuth.getInstance().signOut().run {
                         navController.navigate(ReaderScreens.LoginScreen.name)
                     }
                 }) {
Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout")
                 }
    }, backgroundColor = Color.Transparent, elevation = 0.dp)
}

@Composable
fun FABContent(onTap:()->Unit) {
 FloatingActionButton(onClick = { onTap() }, shape = RoundedCornerShape(50.dp),
 backgroundColor = Color(0xFF0B2B30)
 ) {
     Icon(imageVector = Icons.Default.Add, contentDescription = "Add a book",
     tint =Color.White)
 }
}
