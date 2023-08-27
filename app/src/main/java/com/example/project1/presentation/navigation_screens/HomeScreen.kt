package com.example.project1.presentation.navigation_screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.CompanyActivity
import com.example.project1.data.local.Stock
import com.example.project1.viewmodels.home.HomeScreenViewModel


@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val context = LocalContext.current
    val stockList by viewModel.stockData.collectAsState()

    Surface {
        Column {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Избранное",
                    fontSize = 30.sp
                )
            }
            Divider(Modifier.padding(7.dp))

            Box(modifier = Modifier
                .width(360.dp)
                .align(Alignment.CenterHorizontally)
            ) {
                if (stockList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(stockList) { result ->
                            Row(
                                modifier = Modifier
                                    .clickable {
                                        val intent =
                                            Intent(context, CompanyActivity::class.java).apply {
                                                putExtra("dataKey", result.symbol)
                                            }
                                        context.startActivity(intent)
                                    }
                            ) {
                                CompanyStock(result)
                            }
                            Spacer(modifier = Modifier.height(7.dp))
                            Divider(modifier = Modifier.padding(horizontal = 20.dp))
                            Spacer(modifier = Modifier.height(7.dp))
                        }
                    }
                } else {
                    Text(text = "Нет сохраненных акций")
                }
            }

        }
    }
}


@Composable
fun CompanyStock(stock: Stock) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stock.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stock.type,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stock.symbol,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Preview
@Composable
fun HomeTest() {
}