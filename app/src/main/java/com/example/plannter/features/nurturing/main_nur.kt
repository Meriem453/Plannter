package com.example.plannter.features.nurturing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
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
fun Nur_main(navigator: DestinationsNavigator){
    Column(Modifier.fillMaxSize()) {
        TopBar("Nurturing")
        SearchView{

        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            /*Content4(listOf<Local_plant>(
                Local_plant(ByteArray(100),"lfl,g","zejnfn","eegrrgrg","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","","ffffffffff",5),
                Local_plant(ByteArray(100),"lfl,g","zejnfn","","ffffffffff",5),

                ))*/
            Text(text = "Feature not yet implemented :)",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                color = Color.LightGray,
                textAlign = TextAlign.Center,
            )


        }

        BottomBar(
            list = listOf(
                BarItem(
                    stringResource(id = R.string.catalogues), false,
                    R.drawable.leaf
                ),
                BarItem(
                    stringResource(id = R.string.my_plants), false,
                    R.drawable.plant_pot
                ),
                BarItem(
                    stringResource(id = R.string.nurturing), false,
                    R.drawable.eco_saving
                )

            ),2, navigator
        )
    }
}

@Composable
fun Content4(plants:List<Local_plant>) {
    LazyColumn(content = {
        items(plants.size){
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .fillMaxHeight(), border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)) {
                    Box {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.TopEnd)
                            ,
                            tint = colorResource(id = R.color.background_green)

                        )
                        Image(painter = painterResource(id = R.drawable.eco_saving), contentDescription ="",Modifier.fillMaxWidth().aspectRatio(2f) )
                    }

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
                        color = colorResource(R.color.title_green),
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                top =15.dp,
                                bottom = 15.dp
                            )
                            .fillMaxHeight()


                    )


                }
            }
        }
    })
}
