package com.example.plannter.features.catalogue

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plannter.R
import com.example.plannter.features.my_plants.data.BarItem
import com.example.plannter.features.my_plants.ui.BottomBar
import com.example.plannter.features.my_plants.ui.SearchView
import com.example.plannter.features.my_plants.ui.TopBar
import com.example.plannter.model.local.entities.Local_plant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun catalogue_main(navigator: DestinationsNavigator){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBar("Catalogue")
        SearchView{

        }
        categories()
        Box (modifier = Modifier.weight(1f)){
            MyGridView(listOf<Local_plant>(
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","meriem houda younes","ffffffffff",5),

                ))
        }

        BottomBar(list = listOf(
            BarItem(stringResource(id = R.string.catalogues),false,
                R.drawable.leaf
            ),
            BarItem(stringResource(id = R.string.my_plants),false,
                R.drawable.plant_pot
            ),
            BarItem(stringResource(id = R.string.nurturing),false,
                R.drawable.eco_saving
            )

        ),0,navigator)

    }


}
@Composable
fun cat_element(icon: Int, cat: String) {
Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxHeight(0.1f)){
    Icon(painter = painterResource(id = icon), contentDescription ="" , tint = Color.Gray, modifier = Modifier.fillMaxHeight(0.6f))
    Text(
        text = cat,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(top = 4.dp)
    )

}
}

@Composable
fun categories() {
    Column(Modifier.fillMaxWidth()) {
        Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
            cat_element(R.drawable.eco_saving,"defg")
            cat_element(R.drawable.eco_saving,"defg")
            cat_element(R.drawable.eco_saving,"defg")
            cat_element(R.drawable.eco_saving,"defg")
        }
        Canvas(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            , onDraw = {
            drawLine(
                start = Offset(x = 0f, y = size.height/2),
                end = Offset(x = size.width, y = size.height/2),
                strokeWidth = 1f,
                color = Color.Gray
            )
        })
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyGridView(listOf: List<Local_plant>) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
        items(listOf.size){
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .fillMaxHeight(), border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)) {
Column(
    Modifier
        .fillMaxSize()
        .background(Color.White)) {
 Icon(
        imageVector = Icons.Default.FavoriteBorder,
        contentDescription = "",
     modifier = Modifier
         .align(Alignment.End)
         .padding(10.dp),
     tint = colorResource(id = R.color.background_green)

 )
    Image(painter = painterResource(id = R.drawable.eco_saving), contentDescription ="",Modifier.fillMaxWidth() )
Canvas(modifier = Modifier
    .fillMaxWidth()
    .padding(top = 20.dp), onDraw = {
    drawLine(
        start = Offset(x = 0f, y = size.height/2),
        end = Offset(x = size.width, y = size.height/2),
        strokeWidth = 1f,
        color = Color.Gray
    )
})
    Text(
        text = "Geranium",
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 16.sp,
        color = Color.Gray,
        modifier = Modifier
            .padding(
                start = 10.dp,
                top = 5.dp,
            )
            .fillMaxHeight()

    )
    Text(
        text = "jjkbvh",
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier
            .padding(
                start = 10.dp,
                bottom = 10.dp
            )
            .fillMaxHeight()

    )

}
            }
        }
    }
}
