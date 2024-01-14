package com.jasmeet.memesharer.compose

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.jasmeet.memesharer.compose.ui.MemeAppTheme

class ComposeActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MemeAppTheme {

                val isLoading = remember { mutableStateOf(false) }
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(
                                "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExZTkwMmR5cXVocTB1OTZzaDluaGxjMXhmNHhvejg1NDV3ZDZrcnhvYiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/1p0BGTwi8sZNVZFR44/giphy.gif")
                            .decoderFactory(ImageDecoderDecoder.Factory())
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp)),
                        onLoading = {
                            isLoading.value = true
                        },
                        onSuccess = {
                            isLoading.value = false
                        },
                        onError = {
                            isLoading.value = false
                            Toast.makeText(this@ComposeActivity, "Error loading image", Toast.LENGTH_SHORT).show()
                        }
                    )

                }
                if (isLoading.value){
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }
            }

        }
    }

}

//"https://i.redd.it/psruttriq0cc1.gif"