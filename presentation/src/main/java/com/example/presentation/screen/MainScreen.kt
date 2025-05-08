package com.example.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.presentation.component.MyWorldButton
import com.example.presentation.theme.MyWorldSpaces
import com.example.myWorld.R

@Composable
fun MainScreen(navController: NavHostController) {

    MyWorldScreen.PrimaryScreen {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MyWorldButton.CTA.Xlarge(
                text = stringResource(R.string.main_create_room),
                onClick = {}
            )

            Spacer(modifier = Modifier.height(MyWorldSpaces.Space3))

            MyWorldButton.CTA.Xlarge(
                text = stringResource(R.string.main_create_room),
                onClick = {}
            )
        }
    }
}