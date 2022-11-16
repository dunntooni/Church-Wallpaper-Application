package com.example.churchwallpaperapplication.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.churchwallpaperapplication.R

enum class WelcomeScreen() {
    AssemblyIntro,
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

//        NavHost(
//            navController = navController,
//            startDestination = WelcomeScreen.AssemblyIntro.name,
//            modifier = modifier.padding(innerPadding)
//        ) {
//
//        }
        Column(Modifier.padding(16.dp)) {
            Text(text = "Hello!")
            Text(text = "Welcome to the church wallpaper application.\n" +
                    "Press the button below to get started.")
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Begin")
            }
        }
        // TODO: add NavHost
    }
}