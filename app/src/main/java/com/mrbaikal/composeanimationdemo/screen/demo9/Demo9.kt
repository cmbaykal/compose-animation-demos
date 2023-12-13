package com.mrbaikal.composeanimationdemo.screen.demo9

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

@Composable
fun Demo9() {
    val keyboardController = LocalSoftwareKeyboardController.current

    val (
        nameFocusRequester,
        numberFocusRequester,
        dateFocusRequester,
        cvcFocusRequester
    ) = remember { FocusRequester.createRefs() }

    var nameFocus by remember { mutableStateOf(false) }
    var numberFocus by remember { mutableStateOf(false) }
    var dateFocus by remember { mutableStateOf(false) }
    var cvcFocus by remember { mutableStateOf(false) }

    var nameInput by remember { mutableStateOf("") }
    var numberInput by remember { mutableStateOf("") }
    var dateInput by remember { mutableStateOf("") }
    var cvcInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Demo 9",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )
        CardComponent(
            modifier = Modifier,
            nameValue = { nameInput },
            numberValue = { numberInput },
            dateValue = { dateInput },
            cvcValue = { cvcInput },
            nameFocus = { nameFocus },
            numberFocus = { numberFocus },
            dateFocus = { dateFocus },
            cvcFocus = { cvcFocus },
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(nameFocusRequester)
                .onFocusChanged {
                    nameFocus = it.isFocused
                },
            value = nameInput,
            onValueChange = { nameInput = it },
            label = { Text("Name") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions {
                numberFocusRequester.requestFocus()
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(numberFocusRequester)
                .onFocusChanged {
                    numberFocus = it.isFocused
                },
            value = numberInput,
            onValueChange = { numberInput = it },
            label = { Text("Card Number") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions {
                dateFocusRequester.requestFocus()
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(dateFocusRequester)
                .onFocusChanged {
                    dateFocus = it.isFocused
                },
            value = dateInput,
            onValueChange = { dateInput = it },
            label = { Text("Expire Date") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions {
                cvcFocusRequester.requestFocus()
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(cvcFocusRequester)
                .onFocusChanged {
                    cvcFocus = it.isFocused
                },
            value = cvcInput,
            onValueChange = { cvcInput = it },
            label = { Text("CVC") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions {
                keyboardController?.hide()
            }
        )
    }
}