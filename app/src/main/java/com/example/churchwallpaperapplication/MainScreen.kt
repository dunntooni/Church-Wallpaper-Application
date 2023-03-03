package com.example.churchwallpaperapplication

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.churchwallpaperapplication.data.ScriptureDatabase
import com.example.churchwallpaperapplication.ui.*

enum class MainScreen() {
    Welcome,
    AssemblyIntro,
    VolumeSelection,
    BookSelection,
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

    // Get the dimensions of the phone screen to correctly crop the image
    val configuration = LocalConfiguration.current;

    val heightAndWidth = listOf<Int>(configuration.screenHeightDp, configuration.screenWidthDp)
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
        val context = LocalContext.current
        val db = Room
            .databaseBuilder(context, ScriptureDatabase::class.java, "scripture-database")
            .allowMainThreadQueries()
            .createFromAsset(
                "databases/lds-scriptures-sqlite.db"
            )
            .fallbackToDestructiveMigration()
            .build()
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
                val verseDao = db.VerseDao()

                viewModel.overwriteScripture(
                    volumeId_temp = uiState.volumeId_temp,
                    bookId_temp = uiState.bookId_temp,
                    chapterId_temp = uiState.chapterId_temp,
                    verseId_temp = uiState.verseId_temp)
                var verseId = 1
                var verse_text = ""
                if (uiState.verseId > -1) {
                    verse_text = verseDao.getVerseTextFromVerseId(uiState.verseId)
                }
                AssemblyInfoScreen(
                    onAddScriptureButtonClicked = {
                        navController.navigate(MainScreen.VolumeSelection.name)
                    },
                    onAddImageButtonClicked = {
                        navController.navigate(MainScreen.ImageSelection.name)
                    },
                    onSaveWallpaperButtonClicked = {
                        if (uiState.verseId != -1 && uiState.selectedImage != "") {
                            val imageBitmap = it
                            if (imageBitmap != null) {
                                val imageURI =
                                    saveImageHelper.saveImageToInternalStorage(context, imageBitmap)
                                println(imageURI)
                            }
                        }
                    },
                    volume = uiState.volumeId,
                    book = uiState.bookId,
                    chapter = uiState.chapterId,
                    verse_id = uiState.verseId,
                    verse_text = verse_text,
                    selectedImageName = uiState.selectedImage,
                    dimensions = heightAndWidth,
                )
            }
            // Volume Selection Screen
            composable(route = MainScreen.VolumeSelection.name) {
                val volumeDao = db.VolumeDao()
                var listItems = listOf<String>()
                try {
                    listItems = volumeDao.getVolumeTitleList()
                }
                catch (exception:Throwable) {
                    println(exception)
                    listItems = listOf("error1", "error2")
                }
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onIntOptionSelected = {
                        viewModel.setVolumeId_temp(it)
                        navController.navigate(MainScreen.BookSelection.name)
                    },
                    listItems = listItems
                )
            }
            // Book Selection Screen
            composable(route = MainScreen.BookSelection.name) {
                val bookDao = db.BookDao()
                var listItems = listOf<String>()
                try {
                    listItems = bookDao.getBookTitleList(uiState.volumeId_temp)
                }
                catch (exception:Throwable) {
                    println(exception)
                    listItems = listOf("error1", "error2")
                }
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onIntOptionSelected = {
                        viewModel.setBookId_temp(it)
                        navController.navigate(MainScreen.ChapterSelection.name)
                    },
                    onStringOptionSelected = {
                        viewModel.setBookId_temp(bookDao.getBookIdFromBookTitle(it))
                        navController.navigate(MainScreen.ChapterSelection.name)
                    },
                    listItems = listItems,
                    isBook = true,
                )
            }
            // Chapter Selection Screen
            composable(route = MainScreen.ChapterSelection.name) {
                val chapterDao = db.ChapterDao()
                var listItems = listOf<String>()
                try {
                    listItems = (chapterDao.getChapterNumbersListFromBookId(uiState.bookId_temp)).map { it.toString()}
                }
                catch (exception:Throwable) {
                    println(exception)
                    listItems = listOf("1", "2")
                }
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onIntOptionSelected = {
                        viewModel.setChapterId_temp(chapterDao.getChapterIdFromChapterNumberAndBookId(it, uiState.bookId_temp));
                        navController.navigate(MainScreen.VerseSelection.name);
                    },
                    listItems = listItems,
                    prefix = "Chapter "
                )
            }
            // Verse Selection Screen
            composable(route = MainScreen.VerseSelection.name) {
                val verseDao = db.VerseDao()
                var listItems = listOf<String>()
                try {
                    listItems = (verseDao.getVerseNumberListFromChapterId(uiState.chapterId_temp)).map { it.toString()}
                }
                catch (exception:Throwable) {
                    println(exception)
                    listItems = listOf("1", "2")
                }
                ListSelectionScreen(
                    onSaveButtonClicked = {
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    onIntOptionSelected = {
                        viewModel.setVerseId_temp(verseDao.getVerseIdFromVerseNumberAndChapterId(it, uiState.chapterId_temp))
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    listItems = listItems,
                    prefix = "Verse "
                )
            }
            composable(route = MainScreen.ImageSelection.name) {
                var listItems = (context.assets.list("churchImages"))?.toList();
                ImageSelectionScreen(
                    onImageSelected = {
                        viewModel.setSelectedImage(it)
                        navController.navigate(MainScreen.AssemblyIntro.name)
                    },
                    listItems = listItems as List<String>
                )
            }
        }
    }
}