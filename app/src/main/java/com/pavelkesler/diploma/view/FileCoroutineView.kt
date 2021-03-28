package com.pavelkesler.diploma.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pavelkesler.diploma.files.FileWorkViewModel
import com.pavelkesler.diploma.ui.theme.AmoledBlack
import java.time.LocalDateTime

@Composable
fun FileCoroutineView(navController: NavController, readFileText: List<String>, fileWorkViewModel: FileWorkViewModel) {
    val context: Context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Чтение файла и запись в файл", color= Color.White) },
                backgroundColor = AmoledBlack,
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { fileWorkViewModel.writeIntoFile("Written ${LocalDateTime.now()}", context) }) {
                    Text("Заполнить данными")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { fileWorkViewModel.removeAll(context) }) {
                    Text("Очистить файл")
                }
                if (fileWorkViewModel.loading[0]) CircularProgressIndicator() else Spacer(modifier = Modifier.height(0.dp))
                LazyColumn {
                    items(readFileText) {
                        Text(text = it, modifier = Modifier
                            .padding(16.dp, 4.dp, 4.dp, 4.dp))
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }

            }
        },
    )
}