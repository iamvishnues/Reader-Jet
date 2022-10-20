package com.jetpack.reader.app.screens.home

import android.webkit.WebView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.jetpack.reader.app.R
import com.jetpack.reader.app.components.FABContent
import com.jetpack.reader.app.components.ReaderAppBar
import com.jetpack.reader.app.components.TitleSection
import com.jetpack.reader.app.model.MBook
import com.jetpack.reader.app.navigation.ReaderScreens
@Preview
@Composable
fun ReaderHomeScreen(navController: NavController= NavController(LocalContext.current)) {
Scaffold(topBar = {
ReaderAppBar(title = "ReaderApp", navController = navController)
}, floatingActionButton = { FABContent{

}}) {

    Surface(modifier = Modifier.fillMaxSize()) {
HomeContent(navController)
    }
}
}

@Composable
fun HomeContent(navController: NavController){
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName=if(!email.isNullOrEmpty()){
        FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
    }else{
        "N/A"
    }
    Column(Modifier.padding(2.dp),
    verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading \n" + "activity right now...")
          Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column{
                Icon(imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                modifier= Modifier
                    .clickable {
                        navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                    }
                    .size(45.dp)
                , tint = MaterialTheme.colors.secondaryVariant)
Text(text = currentUserName!!, modifier = Modifier.padding(2.dp),
    style = MaterialTheme.typography.overline, color = Color.Red, fontSize = 15.sp
, maxLines = 1, overflow = TextOverflow.Clip)
            Divider()}
        }
        ListCard()
    }

}


@Preview
@Composable
fun ListCard(book:MBook= MBook("Two states", "Running",
    "Chethan Bhagath","hello world"),
onPressDetails:(String)->Unit={}){
    val context= LocalContext.current
    val resources=context.resources
    val displayMetrics=resources.displayMetrics
    val screenWidth=displayMetrics.widthPixels / displayMetrics.density
   val spacing=10.dp
    Card(shape = RoundedCornerShape(24.dp), backgroundColor = Color.White,
    elevation = 6.dp, modifier = Modifier
            .padding(16.dp)
            .height(242.dp)
            .width(202.dp)
            .clickable {
                onPressDetails.invoke(book.title.toString())
            }) {
Column(modifier=Modifier.width(screenWidth.dp-(spacing*2)),
    horizontalAlignment = Alignment.Start
) {
    Row(horizontalArrangement = Arrangement.Center) {
    Image(painter = rememberAsyncImagePainter(model = "http://books.google.com/books/content?id=JGH0DwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api")
        , contentDescription = "book image", modifier = Modifier
            .height(140.dp)
            .width(100.dp)
            .padding(4.dp))
        Spacer(modifier = Modifier.width(50.dp))
     Column(
         modifier = Modifier.padding(top = 25.dp),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ) {
Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "Favorites",
modifier = Modifier.padding(bottom = 1.dp))
         BookRating(score=3.5)

     }
    }
    Text(text = "Book title", modifier = Modifier.padding(4.dp),
    fontWeight = FontWeight.Bold, maxLines = 2, overflow = TextOverflow.Ellipsis)
    Text(text = "Authors: All...", modifier = Modifier.padding(4.dp),
    style = MaterialTheme.typography.caption)
}
        Row(horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom) {
            RoundedButton(label = "Reading", radius = 70)
        }

    }
}

@Composable
fun BookRating(score: Double=4.5) {
Surface(modifier = Modifier
    .height(70.dp)
    .padding(4.dp),
shape = RoundedCornerShape(56.dp),
elevation = 6.dp, color = Color.White) {
Column(modifier = Modifier.padding(4.dp)) {
    Icon(imageVector = Icons.Filled.StarBorder, contentDescription = "Star",
    modifier = Modifier.padding(3.dp))
Text(text = score.toString(), style = MaterialTheme.typography.subtitle1)
}
}
}

@Preview
@Composable
fun RoundedButton(label:String="Reading",radius:Int=29,onPress:()->Unit={}){
   Surface(modifier = Modifier.clip(RoundedCornerShape(bottomEndPercent = radius,
   topStartPercent = radius,))
   , color = Color(0xff92CBDF)) {
       Column(modifier = Modifier
           .width(90.dp)
           .heightIn(40.dp)
           .clickable {
               onPress.invoke()
           }, verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally){
Text(text = label, style = TextStyle(color = Color.White, fontSize = 15.sp))

       }
   }
}

@Composable
fun ReadingRightNowArea(books:List<MBook>, navController: NavController){

}

