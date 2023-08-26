package com.example.project1.presentation.company_screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.project1.R
import com.example.project1.viewmodels.company_profile.CompanyProfileViewModel
import com.example.project1.viewmodels.company_profile.StockChart


@Composable
fun CompanyScreen(
    viewModel: CompanyProfileViewModel,
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    Surface {
        Surface(
            shadowElevation = 7.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Header(company = state.symbol)
            Box(
                modifier = Modifier,
                contentAlignment = CenterEnd
            ) {
                if (!state.inDatabase) {
                    IconButton(
                        onClick = {
                            viewModel.addToDatabase()
                            Toast.makeText(context, "Добавлено в избранное!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 7.dp)
                    ) {
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_star_outline_24), contentDescription = null)
                    }
                } else {
                    IconButton(
                        onClick = {
                            viewModel.deleteFromDatabase()
                            Toast.makeText(context, "Удалено из избранного!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 7.dp)
                    ) {
                        Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            if (state.candleErrorMessage == null) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(text = "Акции")
                Spacer(modifier = Modifier.height(15.dp))
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
                                Text(
                                    text = it.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = it.exchange,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Страна: ${it.country}",
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 14.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(30.dp))
                            CompanyLogo(logo = it.logo)
                        }

                        Text(
                            text = "Деятельность: ${it.industry}",
                            fontWeight = FontWeight.W500
                        )
                        Text(
                            text = "Первый выпуск акций: ${it.ipo.dropLast(6)}"
                        )
                        Text(
                            text = "Валюта: ${it.currency}"
                        )
                        Text(
                            text = "Капитализация: ${it.marketCapitalization} млн. $"
                        )
                        Text(
                            text = "Выпущено акций: ${it.shareOutstanding} млн."
                        )
                        Row {
                            Text(text = "Веб-страница: ")
                            Hyperlink(url = it.weburl)
                        }

                    }
                }
            } else if (state.companyErrorMessage == null) {
                ProgressIndicator()
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
fun Hyperlink(url: String) {
    val context = LocalContext.current
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)) {
            append(url)
            addStringAnnotation(tag = "URL", annotation = url, start = 0, end = url.length)
        }
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations("URL", offset, offset).firstOrNull()?.let { annotation ->
            // Open the URL in a browser
            // Here you can use an Intent to open the URL in a browser
            // or use any other method you prefer
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
            context.startActivity(intent)
        }
    })
}


@Composable
fun CompanyLogo(logo: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(logo)
            .decoderFactory(SvgDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .clip(AbsoluteRoundedCornerShape(25))
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