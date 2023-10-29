package com.example.project1.presentation.navigation_screens

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.models.settings.SettingsViewModel
import com.example.project1.presentation.sign_in.GoogleAuthUiClient
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val signInState by viewModel.signInState.collectAsState()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == RESULT_OK) {
                CoroutineScope(Dispatchers.IO).launch {
                    val signInResult = googleAuthUiClient.getSignInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                }
            }
        }
    )
    
    LaunchedEffect(key1 = signInState.signInError) {
        signInState.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG)
                .show()
        }
    }

     Surface(
         modifier = Modifier
             .fillMaxSize()
     ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Настройки",
                    fontSize = 30.sp
                )
            }
            Divider(Modifier.padding(7.dp))

            if (signInState.isSignInSuccessful == false) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            CoroutineScope(Dispatchers.IO).launch {
                                val signInIntentSender = googleAuthUiClient.signIn()
                                launcher.launch(
                                    IntentSenderRequest
                                        .Builder(
                                            signInIntentSender ?: return@launch
                                        )
                                        .build()
                                )
                            }
                        }
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Войти")
                }
            } else {
                val profile = googleAuthUiClient.getSignedInUser()
                if (profile != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = profile.username!!)
                        }
                    }
                }
            }

            Divider(Modifier.padding(7.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.clearDatabase()
                        Toast
                            .makeText(context, "База данных очищена!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Очистить базу данных")
            }
            Divider(Modifier.padding(7.dp))

            Spacer(modifier = Modifier.height(12.dp))

        }
    }
}