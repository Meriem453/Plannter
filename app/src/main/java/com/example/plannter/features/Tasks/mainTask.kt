package com.example.plannter.features.Tasks

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plannter.R
import com.example.plannter.features.my_plants.data.BarItem
import com.example.plannter.features.my_plants.ui.BottomBar
import com.example.plannter.features.my_plants.ui.TopBar
import com.example.plannter.model.local.entities.Local_plant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun mainTask(navigator: DestinationsNavigator){

    val viewModel = hiltViewModel<TaskViewModel>()

    Column(Modifier.fillMaxSize()) {
        TopBar("Today's tasks")
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                , contentAlignment = Alignment.TopCenter


        ) {
            Content4(viewModel)



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

            ),2, navigator,""
        )
    }
}

@Composable
fun Content4(viewModel: TaskViewModel) {
if(viewModel.tasks.isEmpty()){

    Text(text = "There are no tasks for today",
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        color = Color.LightGray,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 30.dp)
    )
}
    LazyColumn(content = {

        items(viewModel.tasks.size){


            Row (verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()){
Column(
    Modifier
        .fillMaxWidth(.3f)
        .fillMaxHeight()) {
    Text(
        text = viewModel.tasks[it].time,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 16.sp,
        color = colorResource(R.color.title_green),
        modifier = Modifier
            .padding(
                start = 10.dp,
                top = 15.dp,
                bottom = 15.dp
            )


    )
    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .width(5.dp)
            .fillMaxHeight()

            .align(Alignment.CenterHorizontally),

    )

}


            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .fillMaxHeight(), border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)) {



 
                    val plant by viewModel.getPlant(viewModel.tasks[it].plantId).collectAsState(
                        initial =Local_plant(null,"","","","",0)
                    )
                    plant.image?.let { it1 -> viewModel.getPlantImage(it1).asImageBitmap() }
                        ?.let { it2 ->
                            Image(
                                bitmap = it2, contentDescription = "",
                                Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(2f)
                                ,contentScale = ContentScale.Crop
                            )
                        }





    Canvas(modifier = Modifier
        .fillMaxWidth()
        , onDraw = {
        drawLine(
            start = Offset(x = 0f, y = size.height / 2),
            end = Offset(x = size.width, y = size.height / 2),
            strokeWidth = 1f,
            color = Color.Gray
        )
    })


    Text(
        text = plant.name,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 16.sp,
        color = colorResource(R.color.title_green),
        modifier = Modifier
            .padding(
                start = 10.dp,
                top = 15.dp,
                bottom = 10.dp
            )

    )



    Text(
        text = plant.description,

        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 14.sp,
        color = colorResource(R.color.title_green),
        modifier = Modifier
            .padding(
                start = 10.dp,
                bottom = 15.dp
            )

    )


               }
            }}
        }
    })
}
