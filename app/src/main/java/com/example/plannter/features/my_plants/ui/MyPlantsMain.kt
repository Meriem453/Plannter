package com.example.plannter.features.my_plants.ui



import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plannter.R
import com.example.plannter.features.destinations.AddPlantScreenDestination
import com.example.plannter.features.destinations.MyPlantsMainDestination

import com.example.plannter.features.destinations.catalogue_mainDestination
import com.example.plannter.features.destinations.mainTaskDestination
import com.example.plannter.features.destinations.show_detailsDestination
import com.example.plannter.features.my_plants.viewmodels.MyPlantsViewModel
import com.example.plannter.features.my_plants.data.BarItem
import com.example.plannter.model.local.entities.Local_plant
import com.example.plannter.model.local.entities.SavedPlants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator




            @Destination
            @Composable
             fun MyPlantsMain(navigator: DestinationsNavigator){
                val viewModel = hiltViewModel<MyPlantsViewModel>()
                viewModel.getAllPlants()
                viewModel.getAllSavedPlants()

                    Column( modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                        verticalArrangement = Arrangement.Top) {
                        TopBar("My plants")
                        SearchView(""){
                            viewModel.search=it
                            viewModel.updateSearch()
                        }
                        Column(
                            Modifier
                                .weight(1f)
                                .padding(start = 15.dp, end = 15.dp)) {
                            Content(viewModel.filteredPlants,navigator,"My plants",0.5f)
                            Content(list = viewModel.filteredSavedPlants, navigator =navigator , title = "Saved plants",1f)
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

                        ), 1,
                            navigator,"")
                    }



}

@Composable
fun TopBar(title:String){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(
                colorResource(id = R.color.background_green)
            )
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
            .shadow(1.dp)



    ) {
        Text(text = title,
             fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.title_green),
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                )
                .fillMaxHeight()
                .align(Alignment.CenterVertically)

            )


    }
}
@Composable
fun BottomBar(list:List<BarItem>,screen:Int,navigator: DestinationsNavigator,search:String){

    Row (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
            .padding(bottom = 2.dp)
            .shadow(elevation = 2.dp)
        , horizontalArrangement = Arrangement.SpaceAround){
       list.forEachIndexed{index,item ->
           BarItem(
               icon = item.icon,
               label =item.label ,
               color = if(index==screen) colorResource(id = R.color.selected_item_color) else Color.DarkGray
           ) {

               when(index){
                   0->{navigator.navigate(catalogue_mainDestination(""))}
                   1->{navigator.navigate(MyPlantsMainDestination)}
                   2->{navigator.navigate(mainTaskDestination)}
               }
           }
       }
    }
}
@Composable
fun BarItem(icon:Int,label:String,color: Color,onSelected:() -> Unit){
   Column (Modifier.clickable { onSelected() },
       verticalArrangement = Arrangement.SpaceEvenly){
       Icon(painter = painterResource(id = icon),
           contentDescription =label ,
           tint = color,
           modifier = Modifier
               .fillMaxHeight(0.55f)
               .align(Alignment.CenterHorizontally)
               .padding(top = 10.dp))
       Text(text = label,
           color=color,
           fontSize = 14.sp,
           fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
           modifier = Modifier.align(Alignment.CenterHorizontally),
           fontWeight = FontWeight.Bold )
   }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(init:String,onValueChange:(it:String)->Unit){


    var search by remember {
        mutableStateOf(init)
    }
    Card (
        Modifier
            .background(Color.Transparent)
            .fillMaxHeight(0.12f)
            .padding(start=15.dp, end = 15.dp, top = 15.dp, bottom = 5.dp)
            .background(Color.White)
        , border = BorderStroke(1.dp, Color.LightGray)
    ){
        TextField(
            value = search,
            onValueChange = {
                search = it
                onValueChange(it)

            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = colorResource(id = R.color.title_green),
                unfocusedIndicatorColor = Color.LightGray
            ),
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color.LightGray
                )
            },


        )
    }


}
@Composable
fun Content(list: List<Any?>, navigator: DestinationsNavigator, title:String,fill:Float){
    Column(Modifier.fillMaxHeight(fill)){
        Text(text = title,
            fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.title_green),
            modifier = Modifier.padding(top = 15.dp)

        )

if(list.isEmpty()){

    Text(text = "There is no plant matches your search :(",
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        color = Color.LightGray,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 15.dp)
        )
}else{

LazyRow(modifier = Modifier.fillMaxHeight(), content ={
items(list.size){
    if(list[it]!=null){

        Card(modifier = Modifier
            .fillMaxHeight()
            .width(200.dp)
            .padding(end = 7.dp, top = 15.dp)
            .clickable {
                if (list[it] is Local_plant) {
                    navigator.navigate(show_detailsDestination(list[it] as Local_plant))
                } else {
                    navigator.navigate(catalogue_mainDestination((list[it] as SavedPlants).name))
                }
            }
            , border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)) {
            Box (Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ){
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(Color.White)) {

                    Image(bitmap = BitmapFactory.decodeByteArray(
                       if(list[it] is Local_plant) (list[it] as Local_plant).image else (list[it] as SavedPlants).image ,
                        0,
                        if(list[it] is Local_plant) (list[it] as Local_plant).image!!.size else (list[it] as SavedPlants).image.size).asImageBitmap()
                        , contentDescription =""
                        , modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f),  contentScale = ContentScale.Crop )
                    Canvas(modifier = Modifier
                        .fillMaxWidth()
                        , onDraw = {
                            drawLine(
                                start = Offset(x = 0f, y = size.height/2),
                                end = Offset(x = size.width, y = size.height/2),
                                strokeWidth = 1f,
                                color = Color.Gray
                            )
                        })
                    Text(
                        text = if(list[it] is Local_plant) (list[it] as Local_plant).name else (list[it] as SavedPlants).name,
                        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                top = 5.dp,
                            )


                    )
                    Text(
                        text = if(list[it] is Local_plant) (list[it] as Local_plant).classification else (list[it] as SavedPlants).desc,
                        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                        fontSize = 14.sp,
                        color = Color.Gray,

                        modifier = Modifier
                            .padding(
                                start = 10.dp,
                                bottom = 10.dp
                            )


                    )

                }
                var fav:Boolean by remember {
                    mutableStateOf(false)
                }
                /* Icon(
                     imageVector = if (fav)Icons.Default.Favorite
                     else Icons.Default.FavoriteBorder,
                     contentDescription = "",
                     modifier = Modifier
                         .padding(10.dp)
                         .height(35.dp)
                         .width(35.dp)
                         .clickable {
                             fav = !fav
                             list[it]!!.fav = fav

                         }

                     ,
                     tint = if (list[it]!!.fav) colorResource(id = R.color.background_green) else Color.White

                 )*/
            }
        }}else{
        Card(modifier = Modifier
            .width(200.dp)
            .padding(end = 7.dp, top = 15.dp)
            .clickable {
                navigator.navigate(
                    AddPlantScreenDestination(
                        Local_plant(
                            null,
                            "",
                            "",
                            "",
                            "",
                            kotlin.random.Random.nextInt(100000)
                        )
                    )
                )
            }
            , shape = RoundedCornerShape(20.dp),
            border = BorderStroke(1.dp, Color.LightGray)){
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Add, contentDescription ="",Modifier.fillMaxSize(0.6f), tint = Color.LightGray)
                Text(
                    text = "Add plant",
                    fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 5.dp,
                        ))
            }
        }
        }


}
} )
}}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}