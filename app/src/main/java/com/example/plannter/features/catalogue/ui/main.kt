package com.example.plannter.features.catalogue.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.plannter.R
import com.example.plannter.features.destinations.cat_plant_detailsDestination
import com.example.plannter.features.my_plants.data.BarItem
import com.example.plannter.features.my_plants.ui.BottomBar
import com.example.plannter.features.my_plants.ui.SearchView
import com.example.plannter.features.my_plants.ui.TopBar
import com.example.plannter.model.remote.data.PlantsList.Data
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun catalogue_main(search:String,navigator: DestinationsNavigator){
    val viewModel = hiltViewModel<RemotePlantsViewModel>()


    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)) {
        TopBar("Catalogue")
        SearchView(search){
                viewModel.updateSearch(it)
        }
        categories(viewModel)
        Column (modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally){
            MyGridView(ArrayList(viewModel.filteredPlants),viewModel,navigator)

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

        ),0,navigator,"")

    }


}


@Composable
fun categories(viewModel: RemotePlantsViewModel) {
    val list = listOf(
        BarItem("Frequent",false,R.drawable.rain),
        BarItem("Average",false,R.drawable.water_tap),
        BarItem("Minimum",false,R.drawable.watering_plants)
    )

    Column(Modifier.fillMaxWidth()) {
        LazyRow (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
            items(list.size){
                Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .clickable {
                        viewModel.watering = it
                        viewModel.plantsList = null
                        viewModel.getAllPlants()
                    }
                ){
                    Icon(painter = painterResource(id =list[it].icon), contentDescription ="" , tint = if(viewModel.watering==it) colorResource(
                        id = R.color.title_green
                    )else Color.Gray, modifier = Modifier.fillMaxHeight(0.6f))
                    Text(
                        text = list[it].label,
                        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                        fontSize = 14.sp,
                        color = if(viewModel.watering==it) colorResource(
                            id = R.color.title_green
                        )else Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                }
            }



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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyGridView(list: ArrayList<Data?>,viewModel: RemotePlantsViewModel,navigator: DestinationsNavigator) {
    list.add(null)
    val c =LocalContext.current
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp)) {
        items(list.size, span = {index ->
             if (index == list.size-1)
                 StaggeredGridItemSpan.FullLine
             else
                 StaggeredGridItemSpan.SingleLane
         
        }){
            if(list[it]!=null){
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(7.dp)
                .clickable {
                    viewModel.getPlantDetails(list[it]!!.id)
                       navigator.navigate(cat_plant_detailsDestination())
                }
                , border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)) {
                Box (Modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopEnd
                    ){
Column(
    Modifier
        .fillMaxSize()
        .background(Color.White)) {

    GlideImage(model = list[it]!!.default_image?.original_url, contentDescription ="", modifier = Modifier
        .fillMaxWidth()
        .height(200.dp),  contentScale = ContentScale.Crop )
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
        text = list[it]!!.common_name,
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
        text = list[it]!!.cycle,
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
                    var fav:Boolean by remember {
                        mutableStateOf(false)
                    }
                    Icon(
                        imageVector = if (fav) Icons.Default.Favorite
                        else Icons.Default.FavoriteBorder,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(10.dp)
                            .height(35.dp)
                            .width(35.dp)
                            .clickable {
                                fav = !fav
                                list[it]!!.fav = fav

                          viewModel.savePlant(list[it]!!,c )
                            }

                        ,
                        tint = if (list[it]!!.fav) colorResource(id = R.color.background_green) else Color.White

                    )
                }
            }}else{
                   viewModel.getAllPlants()
               Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                   CircularProgressIndicator(color = colorResource(id = R.color.title_green), modifier = Modifier.padding(10.dp).alpha(
                       if(viewModel.status=="loading") 1f else 0f
                   ))

                   Text(text = viewModel.error,
                       fontSize = 14.sp,
                       fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                       color = Color.LightGray,
                       textAlign = TextAlign.Center,
                       modifier = Modifier.alpha(
                           if(viewModel.status=="error") 1f else 0f
                       )
                   )
               }
            }
        }



    }
}


