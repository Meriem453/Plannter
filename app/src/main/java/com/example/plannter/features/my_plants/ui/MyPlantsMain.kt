package com.example.plannter.features.my_plants.ui


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
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
import com.example.plannter.features.destinations.Nur_mainDestination
import com.example.plannter.features.destinations.catalogue_mainDestination
import com.example.plannter.features.destinations.show_detailsDestination
import com.example.plannter.features.my_plants.viewmodels.MyPlantsViewModel
import com.example.plannter.features.my_plants.data.BarItem
import com.example.plannter.model.local.entities.Local_plant
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.io.ByteArrayOutputStream
import java.util.Random


@OptIn(ExperimentalMaterial3Api::class)

            @Destination
            @Composable
             fun MyPlantsMain(navigator: DestinationsNavigator){
                val viewModel = hiltViewModel<MyPlantsViewModel>()
                viewModel.getAllPlants()

                Scaffold(
                    Modifier.fillMaxSize(),
                    floatingActionButton = {
                    FloatingActionButton(
                        containerColor = colorResource(id = R.color.orange)
                        , modifier = Modifier
                            .clip(RoundedCornerShape(50.dp)),
                        onClick = {
                          navigator.navigate(AddPlantScreenDestination(
                              Local_plant(null,
                                  "",
                                  "",
                                  "",
                                  "",
                                  kotlin.random.Random.nextInt(100000))
                          ))
                        }){
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = colorResource(id = R.color.light_orange),
                            modifier = Modifier.background(
                            colorResource(id = R.color.orange)))
                    }
                }) {values ->
                    val a = values
                    Column( modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                        verticalArrangement = Arrangement.Top) {
                        TopBar("My plants")
                        SearchView{
                            viewModel.search=it
                            viewModel.updateSearch()
                        }
                        Box(Modifier.weight(1f)) {
                            Content(viewModel.filteredPlants,navigator)
                        }
                        /*BottomBar(list = listOf(
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
                            navigator)*/
                    }
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
fun BottomBar(list:List<BarItem>,screen:Int,navigator: DestinationsNavigator){

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
                   0->{navigator.navigate(catalogue_mainDestination)}
                   1->{navigator.navigate(MyPlantsMainDestination)}
                   2->{navigator.navigate(Nur_mainDestination)}
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
fun SearchView(onValueChange:(it:String)->Unit){
    var search by remember {
        mutableStateOf("")
    }
    Card (
        Modifier
            .background(Color.Transparent)
            .fillMaxHeight(0.12f)
            .padding(15.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
        , border = BorderStroke(1.dp, Color.LightGray)
    ){
        TextField(
            value = search,
            onValueChange = {
                search = it
                onValueChange(it)

            },
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
fun Content(list: List<Local_plant>,navigator: DestinationsNavigator){
if(list.isEmpty()){
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


    Text(text = "There is no plant matches your search :(",
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        color = Color.LightGray,
        textAlign = TextAlign.Center
        )}
}else{
LazyColumn(content ={
items(list.size){
    Card (
        border= BorderStroke(1.dp, Color.LightGray),
        modifier= Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .height(100.dp)
            .padding(7.dp)
            .clickable {
                navigator.navigate(show_detailsDestination(list[it]))
            }


    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)

        ) {
            Image(bitmap = BitmapFactory.decodeByteArray(list[it].image,0,list[it].image!!.size).asImageBitmap(),
                contentDescription ="" ,
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(end = 15.dp).fillMaxWidth(0.3f))
            Column (Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly){
                Text(text = list[it].name,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                    color = colorResource(id = R.color.selected_item_color))
                Text(text = list[it].classification,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.josefinsans_light))
                )
            }
        }
    }

}
} )}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}