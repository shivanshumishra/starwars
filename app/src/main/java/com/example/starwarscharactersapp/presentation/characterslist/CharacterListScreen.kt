package com.example.starwarscharactersapp.presentation.characterslist

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.starwarscharactersapp.R
import com.example.starwarscharactersapp.common.Screen
import com.example.starwarscharactersapp.presentation.characterslist.components.BottomSheet
import com.example.starwarscharactersapp.presentation.characterslist.components.CharacterListItem
import com.example.starwarscharactersapp.presentation.characterslist.components.EmptyListScreen
import com.example.starwarscharactersapp.presentation.characterslist.components.ShowChangeFilterView
import com.example.starwarscharactersapp.presentation.ui.theme.ColorPrimary
import com.example.starwarscharactersapp.presentation.ui.theme.TextWhite

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    var showSheet by remember { mutableStateOf(false) }
    var showFilter by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet(
            charactersList = state.characters,
            showFilters = showFilter,
            onDismiss = {
                showSheet = false
            },
            filterSelected = { selectedFilter ->
                viewModel.selectedFilter.value = selectedFilter
            },
            sortSelected = { sortSelected ->
                viewModel.selectedSort.value = sortSelected
            },
            selectedFilter = viewModel.selectedFilter.value,
            selectedSort = viewModel.selectedSort.value,
            onOkClick = {
                if (showFilter) {
                    viewModel.filterItems()
                } else {
                    viewModel.sortItems()
                }
                showSheet = false
            }
        )
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "STAR WARS",
                color = ColorPrimary,
                style = MaterialTheme.typography.headlineLarge,
            )

            Text(
                text = "May the Force be with you",
                color = TextWhite,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = ColorPrimary
                    )
                }
            } else {
                ListHeaderSection(
                    onSortClick = {
                        showSheet = true
                        showFilter = false
                    },
                    onFilterClick = {
                        showSheet = true
                        showFilter = true
                    }
                )
                if (state.characters.isEmpty() || state.error.isNotBlank() && viewModel.selectedFilter.value.isEmpty()) {
                    EmptyListScreen()
                } else if (state.characters.isEmpty() && viewModel.selectedFilter.value.isNotBlank()) {
                    ShowChangeFilterView()
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        items(state.characters) { character ->
                            CharacterListItem(character = character, showCharacterDetails = {
                                val id = it.url.split("people/")[1].replace("/", "").toInt()
                                navController.navigate(Screen.CharacterDetailScreen.route + "/${id}")
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ListHeaderSection(
    onSortClick: () -> Unit,
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    onSortClick()
                }
        ) {
            Text(
                text = "Sort By",
                color = ColorPrimary,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_foreground),
                contentDescription = stringResource(id = R.string.sort_by),
                modifier = Modifier.size(50.dp),
                tint = ColorPrimary
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable {
                    onFilterClick()
                }
        ) {
            Text(
                text = "Filter",
                color = ColorPrimary,
                style = MaterialTheme.typography.bodyLarge,
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_foreground),
                contentDescription = stringResource(id = R.string.filter),
                modifier = Modifier.size(50.dp),
                tint = ColorPrimary
            )
        }
    }
}
