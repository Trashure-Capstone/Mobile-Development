package com.example.trashure.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.*

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    iconVector: ImageVector,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false,
    modifier: Modifier
) {

    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        shape = Shapes_Larger.medium,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryTextColor,
            cursorColor = PrimaryColor,
            backgroundColor = PrimaryBackgroundColor,
            errorBorderColor = ErrorColor
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(iconVector, contentDescription = "")
        },
        isError = !errorStatus
    )
}


@Composable
fun PasswordTextFieldComponent(
    labelValue: String,
    iconVector: ImageVector,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false,
    isLastField:Boolean = true,
    modifier: Modifier
) {

    val localFocusManager = LocalFocusManager.current
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        shape = Shapes_Larger.medium,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryTextColor,
            cursorColor = PrimaryColor,
            backgroundColor = PrimaryBackgroundColor,
            errorBorderColor = ErrorColor
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = if(isLastField) ImeAction.Done else ImeAction.Next
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = {
                localFocusManager.clearFocus()
            }, onNext = {
                localFocusManager.moveFocus(FocusDirection.Down)
        }),
            
        
        maxLines = 1,
        value = password.value,
        onValueChange = {
            password.value = it
            onTextChanged(it)
        },
        leadingIcon = {
            Icon(iconVector, contentDescription = "")
        },
        trailingIcon = {

            val iconImage = if (passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(imageVector = iconImage, contentDescription = description)
            }

        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
}

@Preview
@Composable
fun clickableTextPreview (){
    AnnotatedClickableText(initialText = "Not a member? ", clickableText = "Register", onClick = {}, Modifier)
}


@Composable
fun AnnotatedClickableText(
    initialText: String,
    clickableText: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    val annotatedText = buildAnnotatedString {
        
        withStyle(
            style = SpanStyle(
                color = PrimaryTextColor,
                fontSize = 16.sp,
                fontFamily = Lato,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(initialText)
        }
        
        pushStringAnnotation(
            tag = clickableText,
            annotation = clickableText
        )
        withStyle(
            style = SpanStyle(
                color = PrimaryTextColor,
                fontSize = 16.sp,
                fontFamily = Lato,
                fontWeight = FontWeight.Bold
            )
        ) {
            append(clickableText)
        }
        // when pop is called it means the end of annotation with current tag
        pop()
    }
    
    ClickableText(
        modifier = modifier,
        text = annotatedText,
        onClick = { offset ->
        
            annotatedText.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == clickableText) {
                        onClick()
                    }
                }
        
        }
    )
}







