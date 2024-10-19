package com.draganstojanov.myworld_compose.screens.countryList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.draganstojanov.myworld_compose.R
import com.draganstojanov.myworld_compose.ui.theme.colorPrimary
import com.draganstojanov.myworld_compose.ui.theme.colorSecondary
import com.draganstojanov.myworld_compose.ui.theme.colorWhite
import com.draganstojanov.myworld_compose.viewModel.CountryListViewModel

@Composable
fun SearchTextField(viewModel: CountryListViewModel) {
    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = Modifier.fillMaxWidth()
    )
    {
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                viewModel.searchFilter(it.text)
            },
            label = { Text(stringResource(id = R.string.search_hint)) },
            placeholder = { Text(stringResource(id = R.string.search_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            textStyle = TextStyle(fontSize = 20.sp),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = {
                    inputValue.value = TextFieldValue("")
                    viewModel.searchFilter("")
                    keyboardController?.hide()
                }) {
                    Icon(
                        Icons.Filled.Close,
                        null,
                        tint = colorPrimary,
                    )
                }
            },
            shape = CircleShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colorPrimary,
                focusedTrailingIconColor = colorPrimary,
                cursorColor = colorSecondary,
                focusedContainerColor = colorWhite,
                focusedLabelColor = colorSecondary,
                unfocusedLabelColor = colorSecondary,
                focusedPlaceholderColor = colorSecondary,
                focusedBorderColor = colorPrimary
            )
        )
    }
}

