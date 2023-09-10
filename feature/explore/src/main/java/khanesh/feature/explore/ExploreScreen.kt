package khanesh.feature.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import khanesh.core.ui.resources.R

@Composable
fun ExploreScreen(
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    Box(modifier = modifier.fillMaxSize()) {
        Button(
            onClick = onSearchClicked,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = stringResource(id = R.string.explore))
        }
    }
}
