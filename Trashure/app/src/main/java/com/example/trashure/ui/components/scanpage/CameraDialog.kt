package com.example.trashure.ui.components.scanpage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.trashure.R
import com.example.trashure.ui.theme.*

@Composable
fun CameraDialog(
    onCancelClicked: () -> Unit,
    onCameraSelected: () -> Unit,
    onGallerySelected: () -> Unit
) {
    Dialog(onDismissRequest = onCancelClicked){
        Box(modifier = Modifier
            .clip(shape = Shapes.medium)
            .background(PrimaryBackgroundColor)
            .border(BorderStroke(1.dp, Line_Stroke_1), shape = Shapes.medium)
        ){
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.title_dialog),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier
                            .padding(bottom = 15.dp, top = 15.dp, start = 20.dp)
                    )
                }
                Divider(color = Line_Stroke_1,thickness = 1.dp)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCameraSelected()
                        }
                        .padding(vertical = 10.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.camera_choice_dialog),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Blue_2,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Lato,
                        ),
                        modifier = Modifier
                    )
                }
                Divider(color = Line_Stroke_1,thickness = 1.dp)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onGallerySelected()
                        }
                        .padding(vertical = 10.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.gallery_choice_dialog),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Blue_2,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Lato,
                        ),
                        modifier = Modifier
                    )
                }
                Divider(color = Line_Stroke_1,thickness = 1.dp)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCancelClicked()
                        }
                        .padding(vertical = 10.dp)
                ){
                    Text(
                        text = stringResource(id = R.string.close),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            color = Blue_2,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Lato,
                        ),
                        modifier = Modifier
                    )
                }
                
            }
        }
    }
}

@Preview
@Composable
fun CameraDialogPreview(){
    CameraDialog(onCancelClicked = { /*TODO*/ }, onCameraSelected = { /*TODO*/ }) {
        
    }
}