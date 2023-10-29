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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.CompanyActivity
import com.example.project1.data.remote.Lookup
import com.example.project1.models.search.SearchEvent
import com.example.project1.models.search.SearchState
import com.example.project1.models.search.SearchViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val context = LocalContext.current
    val state = viewModel.state

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBar(viewModel = viewModel, state = state)

            if (viewModel.state.isSearching and state.query.isNotEmpty()) {
                ProgressIndicator()
            } else {
                if (state.query.isNotEmpty()) Text(
                    text = "Найдено: ${state.searchCount}",
                    modifier = Modifier.padding(7.dp),
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    )

                Box(modifier = Modifier
                    .width(360.dp)
                    .align(Alignment.CenterHorizontally)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(state.searchResults) { result ->
                            if (result.type != "") {
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
                                    CompanyItem(item = result)
                                }
                                Spacer(modifier = Modifier.height(7.dp))
                                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                                Spacer(modifier = Modifier.height(7.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: SearchViewModel, state: SearchState) {
    TextField(
        value = state.query,
        onValueChange = {
            viewModel.state.isSearching = true
            viewModel.onEvent(
                SearchEvent.OnSearchQueryChange(it)
            )
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(100)),
        placeholder = { Text(text = "Тикер, isin или cusip...") },
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            if (state.query.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Очистить",
                    modifier = Modifier
                        .clickable {
                            viewModel.onEvent(
                                SearchEvent.OnSearchQueryChange("")
                            )
                        }
                )
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = { viewModel.onEvent(
                SearchEvent.OnSearchQueryChange(state.query)
            ) }
        )
    )
}

@Composable
fun ProgressIndicator() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun CompanyItem(item: Lookup) {
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
                    text = item.description,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = item.type,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.symbol,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Preview
@Composable
fun SearchTest() {
    Surface {
        CompanyItem(item = Lookup("APPLE INC", "AAPL", "Common Stock"))
    }
}