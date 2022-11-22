package com.example.churchwallpaperapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.churchwallpaperapplication.ui.*

enum class MainScreen() {
    Welcome,
    AssemblyIntro,
    BigBookSelection,
    SmallBookSelection,
    ChapterSelection,
    VerseSelection,
    ImageSelection,
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 * Stolen from https://developer.android.com/codelabs/basic-android-kotlin-compose-navigation
 */
@Composable
fun AppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun WallpaperApp(modifier: Modifier = Modifier, viewModel: ImageViewModel = viewModel()) {
    val navController = rememberNavController()
    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen

    Scaffold(
        topBar = {
            AppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MainScreen.Welcome.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = MainScreen.Welcome.name) {
                WelcomeScreen(
                    onBeginButtonClicked = { // Edit these to change what happens when screens are changed.
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    }
                )
            }
            composable(route = MainScreen.AssemblyIntro.name) {
                AssemblyInfoScreen(
                    onAddScriptureButtonClicked = {
                        navController.navigate(MainScreen.BigBookSelection.name)
                    },
                    onAddImageButtonClicked = {
                        navController.navigate(MainScreen.ImageSelection.name)
                    },
                    onSaveWallpaperButtonClicked = {

                    }
                )
            }
            composable(route = MainScreen.BigBookSelection.name) {
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onOptionSelected = {
                        navController.navigate(MainScreen.SmallBookSelection.name)
                    },
                    listItems = mutableListOf(stringResource(R.string.test_big_book_1), stringResource(R.string.test_big_book_2))
                )
            }
            composable(route = MainScreen.SmallBookSelection.name) {
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onOptionSelected = {
                        navController.navigate(MainScreen.ChapterSelection.name)
                    },
                    listItems = mutableListOf(stringResource(R.string.test_book_1), stringResource(R.string.test_book_2))
                )
            }
            composable(route = MainScreen.ChapterSelection.name) {
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onOptionSelected = {
                        navController.navigate(MainScreen.VerseSelection.name)
                    },
                    listItems = mutableListOf(stringResource(R.string.test_chapter_1), stringResource(R.string.test_chapter_2))
                )
            }
            composable(route = MainScreen.VerseSelection.name) {
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onOptionSelected = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    listItems = mutableListOf(stringResource(R.string.test_verse_1), stringResource(
                                            R.string.test_verse_2)
                                        )
                )
            }
            composable(route = MainScreen.ImageSelection.name) {
                ImageSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    }
                )
            }
        }
    }
}