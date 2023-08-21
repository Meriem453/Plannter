package com.example.plannter.features.my_plants.ui


import android.graphics.BitmapFactory
import android.net.Uri

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plannter.R
import com.example.plannter.features.my_plants.viewmodels.AddPlantViewModel
import com.example.plannter.model.local.entities.Local_plant
import com.ramcosta.composedestinations.annotation.Destination
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime


@Destination
@Composable
fun AddPlantScreen(plant:Local_plant){
    val viewModel= hiltViewModel<AddPlantViewModel>()
  if(!viewModel.init){
      viewModel.plant=plant
      viewModel.init=true
  }
    val launcher = rememberLauncherForActivityResult(contract =

        ActivityResultContracts.GetContent()) { uri: Uri? ->
         viewModel.imageUri = uri
            viewModel.updateBitmap()


    }
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        TopBar("Add plant")
        if(viewModel.plant.image!=null){
            Image(bitmap = BitmapFactory.decodeByteArray(viewModel.plant.image,0,viewModel.plant.image!!.size).asImageBitmap() ,
                contentDescription ="",
                Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(200.dp)
                    )
                    .shadow(2.dp)
                    .padding(30.dp),
                contentScale = ContentScale.Crop
            )
        }else{
            Image(painter = painterResource(id = R.drawable.leaf) ,
                contentDescription ="",
                Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(200.dp)
                    )
                    .shadow(2.dp)
                    .padding(30.dp)
            )
        }

        Row(horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()) {
            Icon(painter = painterResource(id = R.drawable.insert_photo),
                tint = colorResource(id = R.color.background_green),
                contentDescription ="",
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .padding(end = 20.dp)
                    .clickable {
                        launcher.launch("image/*")
                    }
            )


        }
        Card(
            shape = RoundedCornerShape(topStart = 20.dp,
                topEnd = 20.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .weight(1f)) {
            CardContent2(viewModel)

        }

    }
}

@Composable
fun CardContent2(viewModel: AddPlantViewModel) {
val context= LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 20.dp)
    ){
        FillText(viewModel.plant.name,"Name",) {
            viewModel.plant = Local_plant(
                image = viewModel.plant.image,
                name = it,
                place = viewModel.plant.place,
                classification = viewModel.plant.classification,
                description = viewModel.plant.description,
                id = viewModel.plant.id
                )
        }
        FillText(viewModel.plant.place,"Age"){
            viewModel.plant = Local_plant(
                image = viewModel.plant.image,
                name = viewModel.plant.name,
                place = it,
                classification = viewModel.plant.classification,
                description = viewModel.plant.description,
                id = viewModel.plant.id
            )

        }
        FillText(viewModel.plant.classification,
            title ="Classifications (leave a space)" ){
            viewModel.plant = Local_plant(
                image = viewModel.plant.image,
                name = viewModel.plant.name,
                place = viewModel.plant.place,
                classification = it,
                description = viewModel.plant.description,
                id = viewModel.plant.id
            )

        }
        FillText(info = viewModel.plant.description, title = "Descripiton"){
            viewModel.plant = Local_plant(
                image = viewModel.plant.image,
                name = viewModel.plant.name,
                place = viewModel.plant.place,
                classification = viewModel.plant.classification,
                description = it,
                id = viewModel.plant.id
            )

        }

        Text(text = "Reminders",color = colorResource(id = R.color.title_green), fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 18.sp, modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp))
viewModel.getReminders(viewModel.plant.id)
        if(viewModel.reminders.isEmpty()) {
            Text(
                text = "There are no reminders",
                color = Color.LightGray,
                fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp)
            )
        }else{

            viewModel.reminders.forEachIndexed { index, reminder ->
                Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                    .fillMaxWidth()){
                    Text(text =reminder.time, fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 18.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                    Card (modifier = Modifier.fillMaxWidth(0.7f), border = BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(20.dp)){
                        Column (modifier = Modifier
                            .background(Color.White)
                            .fillMaxSize()){

                            Row( modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 10.dp)){
                                reminder.date.forEachIndexed { index, c ->
                                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = "", modifier = Modifier.height(20.dp),
                                        tint = colorResource(id = if (c =='1') R.color.background_green else R.color.orange)
                                    )
                                }
                            }
                            Text(text =reminder.desc, color = colorResource(id = R.color.title_green), fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 16.sp, modifier = Modifier.padding(10.dp))
                            Icon(painter = painterResource(id = R.drawable.delete),contentDescription = "", modifier = Modifier
                                .height(48.dp)
                                .width(48.dp)
                                .align(Alignment.End)
                                .padding(10.dp)
                                .clickable {
                                    viewModel.deleteReminder(reminder)
                                })

                        }
                    }

                }

            }
        }

        Button( modifier = Modifier
            .fillMaxHeight(0.05f)
            .padding(start = 30.dp, top = 20.dp, bottom = 30.dp)
            .clip(RoundedCornerShape(20.dp))
            // .background(colorResource(id = R.color.background_green))
            .shadow(1.dp)
           ,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_green)),
           onClick = {
               if(viewModel.saved){
                   viewModel.showDialog=true
               }
               else{
                   Toast.makeText(context,"You have to save the plant first",Toast.LENGTH_LONG).show()
               }

           }) {
           Text(text = "Add reminder", color = colorResource(id = R.color.title_green), fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
       }
        Button( modifier = Modifier
            .fillMaxHeight(0.05f)
            .padding(end = 30.dp, top = 20.dp, bottom = 30.dp)
            .clip(RoundedCornerShape(20.dp))
            //.background(colorResource(id = R.color.background_green))
            .shadow(1.dp)
            .align(Alignment.End)
            ,colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_green)),
            onClick = {
                viewModel.insertPlant()
                if(viewModel.saved)
                Toast.makeText(context,"Plant saved",Toast.LENGTH_LONG).show()
            }) {
            Text(text = "Save",
                color = colorResource(id = R.color.title_green),
                fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp))
        }
    }

    if(viewModel.showDialog){
        Dialog(onDismissRequest = { viewModel.showDialog = false }) {
            AddTimingDialog(viewModel)
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FillText(info:String,title:String,onValueChange : (value:String) -> Unit) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.josefinsans_regular)),
        fontSize = 16.sp,
        color = colorResource(id = R.color.title_green),
        modifier = Modifier
            .padding(
                start = 30.dp,
                )

    )
    OutlinedTextField(value = info,
        onValueChange = {onValueChange(it)},
        Modifier
            .fillMaxWidth()
            .padding(
                top = 5.dp,
                start = 30.dp,
                end = 30.dp
            ),

    )
}
@Composable
fun AddTimingDialog(viewModel: AddPlantViewModel){
val context= LocalContext.current
    val dialogState= rememberMaterialDialogState()

    Column(modifier = Modifier
        .background(Color.White)
        ) {

        Text(text = "Add reminder",
            fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.title_green),
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                )
                .align(Alignment.Start)


        )
        Text(text = viewModel.formattedTime,
            fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                )
                .align(Alignment.CenterHorizontally)
                .clickable {
                    dialogState.show()
                }
        )
        Text(text = "Week Days",
            fontFamily =  FontFamily(Font(R.font.josefinsans_regular)),
            fontSize = 18.sp,
            color = colorResource(id = R.color.title_green),
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 15.dp,
                )
                .align(Alignment.Start)

        )
        val list = listOf("Sun","Mon","Tus","Wed","Thi","Fri","Sat")
        LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, content = {
            items(list.size){
                Card (modifier = Modifier
                    .clickable {
                        val tmp = viewModel.days.toMutableList()
                        tmp[it] = !tmp[it]
                        viewModel.days = tmp
                    }
                    ,
                    shape = RoundedCornerShape(10.dp),
                    border = BorderStroke(0.5.dp, Color.LightGray)){
                    Row ( modifier = Modifier.background(
                        if(viewModel.days[it]){colorResource(id = R.color.background_green)}else{ Color.White}
                        )){
                        Text(text = list[it],
                            fontFamily = FontFamily(Font(R.font.josefinsans_light)),
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.title_green),
                            modifier = Modifier.padding(5.dp)

                        )
                    }

                }
            }
        }

        )

            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                RadioButton(selected =!viewModel.checkedRepeat ,
                    onClick = { viewModel.checkedRepeat=false })
                Text(text = "Once",
                    fontFamily = FontFamily(Font(R.font.josefinsans_light)),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.title_green),
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center
                )

                RadioButton(selected =viewModel.checkedRepeat ,
                    onClick = { viewModel.checkedRepeat=true })

                Text(text = "Repeat",
                    fontFamily = FontFamily(Font(R.font.josefinsans_light)),
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.title_green),
                    modifier = Modifier.padding(5.dp),
                    textAlign = TextAlign.Center
                )
            }

        FillText(info = viewModel.timingDesc, title = "Description" , onValueChange = {
            viewModel.timingDesc=it
        })
        Button( modifier = Modifier
            .padding(end = 30.dp, top = 20.dp, bottom = 30.dp)
            .clip(RoundedCornerShape(20.dp))
            //.background(colorResource(id = R.color.background_green))
            .shadow(1.dp)
            .align(Alignment.End)
,colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.background_green))
            ,
            onClick = {
                if(viewModel.timingDesc!=""){
                    viewModel.sceduleAlarm()
                    viewModel.showDialog=false
                }
                else{
                    Toast.makeText(context,"Add a description",Toast.LENGTH_LONG).show()
                }

            }) {
            Text(text = "Save", color = colorResource(id = R.color.title_green), fontFamily = FontFamily(Font(R.font.josefinsans_regular)), fontSize = 16.sp, modifier = Modifier.padding(start = 10.dp, end = 10.dp))
        }

    }




    MaterialDialog(
        dialogState = dialogState,
        buttons = {positiveButton(text = "Save") }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a time"
        ){
          viewModel.pickedTime = it
        }
    }
}

