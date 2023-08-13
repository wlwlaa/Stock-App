package com.example.project1.presentation.company_screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.project1.viewmodels.company_profile.CompanyProfileViewModel

@Composable
fun CompanyScreen(viewModel: CompanyProfileViewModel) {
    val context = LocalContext.current
    val state = viewModel.state
//    val painter = rememberAsyncImagePainter(
//        model = ImageRequest.Builder(context.applicationContext)
//            .data(state.companyProfile?.logo)
//            .decoderFactory(SvgDecoder.Factory())
//            .error(androidx.core.R.drawable.ic_call_answer)
//            .placeholder(androidx.core.R.drawable.notification_template_icon_low_bg)
//            .build()
//    )

    Surface {
        Header(company = state.symbol)
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Text(text = state.candles.toString())
            Column {
                state.companyProfile?.let {
                    Column {
                        Row {
                            Column {
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
                            AsyncImage(model = it.logo, contentDescription = "Company logo")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Деятельность: ${it.industry}",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Страна: ${it.country}",
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth(),
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = it.weburl!!,
                            fontSize = 12.sp,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = state.errorMessage ?: "",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


@Composable
fun Header(company: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
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


@Preview
@Composable
fun CompanyScreenPreview(){
    val company = "AAPL"
    val country = "US"
    val currency = "USD"
    val exchange = "NASDAQ NMS - GLOBAL MARKET"
    val industry = "Technology"
    val ipo = "1980-12-12"
    val logo = "https://static2.finnhub.io/file/publicdatany/finnhubimage/stock_logo/AAPL.svg"
    val marketCapitalization = 2813078.475248
    val name = "Apple Inc"
    val shareOutstanding = 15728.7
    val ticker = "AAPL"
    val weburl = "https://www.apple.com/"

    Surface {
        Header(company = name)

    }
}

@Preview
@Composable
fun ScreenPreview() {
    Spacer(modifier = Modifier.height(12.dp))
    Divider(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp))

}