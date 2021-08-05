package me.bytebeats.analyzer.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import me.bytebeats.analyzer.BuildConfig
import me.bytebeats.analyzer.OkHttpAnalyzerInterceptor
import me.bytebeats.analyzer.app.MainActivity.Companion.JSON_URL
import me.bytebeats.analyzer.app.ui.theme.OkHttpAnalyzorTheme
import okhttp3.*
import retrofit2.Retrofit
import java.io.IOException


class MainActivity : ComponentActivity() {
    companion object {
        val JSON_URL =
            "https://raw.githubusercontent.com/itkacher/OkHttpProfiler/master/colors.json"
        var mClient: OkHttpClient? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OkHttpAnalyzorTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }

        //OkHttp Initialization
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkHttpAnalyzerInterceptor())
        }
        mClient = builder.build()
        sendRequest()

        //Retrofit Initialization (if needed)

        //Retrofit Initialization (if needed)
        val retrofit = Retrofit.Builder()
            .client(mClient!!)
            .build()
    }
}

private fun sendRequest() {
    val request: Request = Request.Builder()
        .url(JSON_URL)
        .get()
        .build()

    MainActivity.mClient?.newCall(request)?.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {}
        override fun onResponse(call: Call, response: Response) {
            try {
                if (response.body != null) {
                    val unusedText: String = response.body?.string() ?: ""
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    })
}

@Composable
fun Greeting(name: String) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Red,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                background = Color.LightGray,
            )
        ) {
            append("Hello $name!")
        }
    }
    ClickableText(text = annotatedText, onClick = { sendRequest() })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OkHttpAnalyzorTheme {
        Greeting("Android")
    }
}