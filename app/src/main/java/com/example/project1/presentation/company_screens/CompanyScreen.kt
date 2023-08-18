package com.example.project1.presentation.company_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.project1.viewmodels.company_profile.CompanyProfileViewModel
import com.example.project1.viewmodels.company_profile.StockChart


@Composable
fun CompanyScreen(
    viewModel: CompanyProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    Surface {
        Surface(shadowElevation = 7.dp) {
            Header(company = state.symbol)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            if (state.candleErrorMessage == null) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(text = "Акции")
                Spacer(modifier = Modifier.height(12.dp))
                StockChart(
                    infos = state.candles,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .align(CenterHorizontally)
                )
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Divider(Modifier.padding(20.dp))
            Spacer(modifier = Modifier.padding(12.dp))



            if (state.companyProfile != null) {

                state.companyProfile?.let {
                    Column {
                        Row {
                            Column {
                                Row {
                                    Text(
                                        text = it.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = it.ticker,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 14.sp,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = it.exchange,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 14.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Не удалось загрузить профиль: ${state.companyErrorMessage}",
                        fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}


@Composable
fun CompanyLogo(logo: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(logo)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null
    )
}


@Composable
fun Header(
    company: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
    ) {
        Text(
            text = company,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 30.sp,
        )
        Divider()
    }
}


@Composable
fun ProgressIndicator() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}