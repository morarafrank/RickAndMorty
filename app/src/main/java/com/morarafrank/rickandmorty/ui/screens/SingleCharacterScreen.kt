package com.morarafrank.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.morarafrank.rickandmorty.R
import com.morarafrank.rickandmorty.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleCharacterScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Character Details",
                        style = Typography.bodySmall
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back Arrow"
                        )
                    }
                },

            )
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
                    .fillMaxSize()
            ) {

            }
        }
    )
}