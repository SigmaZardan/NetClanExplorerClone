package com.example.netclanexplorer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.netclanexplorer.R
import com.example.netclanexplorer.ScaffoldViewState
import com.example.netclanexplorer.data.DataSource
import com.example.netclanexplorer.data.DataSource.MAX_AVAILABLE_STATUS_COUNT
import com.example.netclanexplorer.data.DataSource.purposesList

@Composable
fun RefineScreen(
    scaffoldState: MutableState<ScaffoldViewState>, onBackButtonClicked: () -> Unit,
    onSaveAndExploreButtonClicked: () -> Unit
) {
    LaunchedEffect(Unit) {
        scaffoldState.value = ScaffoldViewState(title = {
            Text(
                "Refine",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displayMedium
            )
        }, navigationIcon = {
            IconButton(onClick = { onBackButtonClicked() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier.size(34.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )

            }
        })

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SelectAvailabilityItem(
            modifier = Modifier.padding(bottom = 12.dp),
            availabilityOptions = DataSource.availabilityOptions
        )
        AddStatusItem()
        SelectHyperLocalDistanceItem()
        SelectPurposeItem()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = onSaveAndExploreButtonClicked) {
                Text("Save & Explore", modifier = Modifier.padding(start = 8.dp, end = 8.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RefineScreenPreview() {
    val scaffoldState = remember { mutableStateOf(ScaffoldViewState()) }
    RefineScreen(scaffoldState = scaffoldState,
        onBackButtonClicked = {},
        onSaveAndExploreButtonClicked = {})
}


@Composable
fun AddStatusItem(modifier: Modifier = Modifier) {
    var status by rememberSaveable { mutableStateOf("") }
    val currentStatusCharacterCount = status.length

    Column(modifier = modifier.padding(bottom = 12.dp)) {
        Text(
            "Add Your Status", style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = status, onValueChange = {
                if (it.length <= MAX_AVAILABLE_STATUS_COUNT) status = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.primary,

                ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            shape = RoundedCornerShape(25)
        )
        Text(
            "${currentStatusCharacterCount}/${MAX_AVAILABLE_STATUS_COUNT}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 4.dp), textAlign = TextAlign.End
        )


    }
}

@Composable
fun SelectHyperLocalDistanceItem() {
    var sliderPosition by rememberSaveable {
        mutableFloatStateOf(0f)
    }


    val finiteEnd = true
    val valueRange = 0f..100f
    val labelMinWidth = 24.dp

    Column(
        modifier = Modifier.padding(bottom = 40.dp)
    ) {
        Text(
            "Select Hyper local Distance", style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val offset = getSliderOffset(
                value = sliderPosition,
                valueRange = valueRange,
                boxWidth = maxWidth,
                labelWidth = labelMinWidth + 8.dp
            )

            val endValueText = if (!finiteEnd && sliderPosition >= valueRange.endInclusive)
                "${sliderPosition.toInt()} +" else sliderPosition.toInt().toString()

            if (sliderPosition > valueRange.start) {
                SliderLabel(
                    label = endValueText,
                    minWidth = labelMinWidth,
                    modifier = Modifier
                        .padding(start = offset)
                )
            }
            Spacer(Modifier.height(height = 40.dp))
        }

        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            },
            valueRange = valueRange,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "1 Km")
            Text(text = "100 Km")
        }


    }
}


@Preview(showBackground = true)
@Composable
fun SelectPurposeItem() {
    Column(
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        Text(
            "Select Purpose", style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        val selectedItems = remember {
            mutableListOf<String>()
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            items(purposesList) { button ->
                var isSelected by rememberSaveable { mutableStateOf(false) }
                OutlinedButton(
                    onClick = {
                        isSelected = !isSelected
                        if (selectedItems.contains(button)) {
                            selectedItems.remove(button)
                        } else {
                            selectedItems.add(button)

                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,
                        contentColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                    )
                ) {

                    Text(button)
                }
            }
        }
    }
}

@Composable
fun SliderLabel(
    label: String = "anything",
    minWidth: Dp = 40.dp,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.bubble_middle_bottom_fill),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.height(35.dp)
        )
        Text(
            text = label,
            textAlign = TextAlign.Center,
            color = androidx.compose.ui.graphics.Color.White,
            modifier = Modifier

                .padding(top = 4.dp, start = 3.dp)
                .defaultMinSize(minWidth = minWidth)
        )
    }


}

private fun getSliderOffset(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    boxWidth: Dp,
    labelWidth: Dp
): Dp {
    val coerced = value.coerceIn(valueRange.start, valueRange.endInclusive)
    val positionFraction = calcFraction(valueRange.start, valueRange.endInclusive, coerced)

    return (boxWidth - labelWidth) * positionFraction
}

private fun calcFraction(a: Float, b: Float, pos: Float) =
    (if (b - a == 0f) 0f else (pos - a) / (b - a)).coerceIn(0f, 1f)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAvailabilityItem(
    modifier: Modifier = Modifier, availabilityOptions: List<String>
) {
    var isExpanded by remember { mutableStateOf(false) }
    var currentOptionSelected by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Text(
            "Select Your Availability", style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }) {


            OutlinedTextField(

                value = currentOptionSelected,
                placeholder = {
                    Text(availabilityOptions[0])
                },
                readOnly = true,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                singleLine = true,
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary
                ),


                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                shape = RoundedCornerShape(25),
            )
            ExposedDropdownMenu(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
            ) {
                availabilityOptions.forEach { option ->
                    AvailabilityDropdownMenuItem(
                        currentOption = option,
                        onOptionSelected = {
                            currentOptionSelected = it
                            isExpanded = false
                        })
                }
            }
        }


    }
}


@Composable
fun AvailabilityDropdownMenuItem(
    currentOption: String, onOptionSelected: (String) -> Unit
) {
    DropdownMenuItem(
        text = { Text(currentOption) },
        onClick = { onOptionSelected(currentOption) },
        colors = MenuDefaults.itemColors(
            textColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SelectAvailabilityItemPreview() {
    SelectAvailabilityItem(availabilityOptions = listOf("Man", "Women", "Gay"))

}