package com.example.netclanexplorer.screens.commonUI

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.netclanexplorer.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommercePanelTopAppBar() {
    var searchText by rememberSaveable { mutableStateOf("") }
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            OutlinedTextField(
                value = searchText,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { searchText = it },
                shape = RoundedCornerShape(50),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search, contentDescription = "search",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                },
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "clear",
                        tint = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                },
                placeholder = {
                    Text(
                        "Search", style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    cursorColor = MaterialTheme.colorScheme.outline,
                    focusedTextColor = MaterialTheme.colorScheme.outline
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { }
                ),
            )
        },
        actions = {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .size(30.dp),
                contentDescription = null,
            )
        }

    )
}

@Preview(showBackground = true)
@Composable
fun CommercePanelTopAppBarPreview() {
    CommercePanelTopAppBar()

}