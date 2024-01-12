package com.example.starwarscharactersapp.presentation.characterslist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.starwarscharactersapp.R
import com.example.starwarscharactersapp.common.CREATED
import com.example.starwarscharactersapp.common.NAME
import com.example.starwarscharactersapp.common.None
import com.example.starwarscharactersapp.common.UPDATED
import com.example.starwarscharactersapp.domain.model.SWCharacter
import com.example.starwarscharactersapp.presentation.ui.theme.ColorPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    charactersList: List<SWCharacter>,
    onDismiss: () -> Unit,
    showFilters: Boolean,
    filterSelected : (String) -> Unit,
    sortSelected : (String) -> Unit,
    selectedSort : String?,
    selectedFilter: String?,
    onOkClick: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    val sortOptions = listOf(
        NAME.sortType,
        CREATED.sortType,
        UPDATED.sortType,
        None.sortType
    )

    val filterOptions = listOf("Gender : Male","Gender : Female","Gender : Not Applicable","Clear")

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = if(showFilters) "Filters" else "Sort By", style = MaterialTheme.typography.headlineMedium)
        }
        LazyColumn {
            val showList = if(showFilters) filterOptions else sortOptions
            items(showList) { option ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp)
                        .clickable {
                            if (showFilters) {
                                filterSelected(option)
                            } else {
                                sortSelected(option)
                            }
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = option)
                    if(showFilters){
                        if(selectedFilter?.contains(option) == true){
                            Icon(
                                painter = painterResource(R.drawable.ic_check_foreground),
                                contentDescription = stringResource(id = R.string.sort_by),
                                modifier = Modifier.size(30.dp),
                                tint = ColorPrimary
                            )
                        }
                    } else {
                        if(selectedSort == option){
                            Icon(
                                painter = painterResource(R.drawable.ic_check_foreground),
                                contentDescription = stringResource(id = R.string.sort_by),
                                modifier = Modifier.size(30.dp),
                                tint = ColorPrimary
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                end = 16.dp,
                bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "OK",
                color = ColorPrimary,
                modifier = Modifier.clickable {
                    onOkClick()
                }.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())
    }
}