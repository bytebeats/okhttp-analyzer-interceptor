# OkHttpAnalyzer

[![GitHub latest commit](https://badgen.net/github/last-commit/bytebeats/OkHttpAnalyzer)](https://github.com/bytebeats/OkHttpAnalyzer/commit/)
[![GitHub contributors](https://img.shields.io/github/contributors/bytebeats/OkHttpAnalyzer.svg)](https://github.com/bytebeats/OkHttpAnalyzer/graphs/contributors/)
[![GitHub issues](https://img.shields.io/github/issues/bytebeats/OkHttpAnalyzer.svg)](https://github.com/bytebeats/OkHttpAnalyzer/issues/)
[![Open Source? Yes!](https://badgen.net/badge/Open%20Source%20%3F/Yes%21/blue?icon=github)](https://github.com/bytebeats/OkHttpAnalyzer/)
[![GitHub forks](https://img.shields.io/github/forks/bytebeats/OkHttpAnalyzer.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/bytebeats/OkHttpAnalyzer/network/)
[![GitHub stars](https://img.shields.io/github/stars/bytebeats/OkHttpAnalyzer.svg?style=social&label=Star&maxAge=2592000)](https://github.com/bytebeats/OkHttpAnalyzer/stargazers/)
[![GitHub watchers](https://img.shields.io/github/watchers/bytebeats/OkHttpAnalyzer.svg?style=social&label=Watch&maxAge=2592000)](https://github.com/bytebeats/OkHttpAnalyzer/watchers/)

An intellij platform plugin. To analyze details of requests and responses from OkHttp.

## Tech stack and whys
* [Kotlin](https://kotlinlang.org/) - Kotlin on Android is a “first-class” language and it has [a lot of benefits](https://developer.android.com/kotlin).
* [MVVM & MVI Architecture](https://developer.android.com/jetpack/guide) - Modern, maintainable, and Google suggested app architecture.
* [Coroutine](https://developer.android.com/kotlin/coroutines) + [Flow](https://developer.android.com/kotlin/flow) = [MVI](https://github.com/Kotlin-Android-Open-Source/MVI-Coroutines-Flow)
* [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client for Android and Java.
* [OkHttp](https://square.github.io/okhttp/) - Meticulous HTTP client for the JVM, Android, and GraalVM.
* [Moshi](https://github.com/square/moshi) - Modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
* [Jetpack Components](https://developer.android.com/jetpack) - Compose, ViewModel and more.

## How to use

##### For OkHttp
###### Java
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
     if (BuildConfig.DEBUG) {
         builder.addInterceptor(new OkHttpAnalyzerInterceptor());
     }   
    OkHttpClient client = builder.build(); 

###### Kotlin
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(OkHttpAnalyzerInterceptor() )
    }    
    val client = builder.build()

##### For Retrofit
###### Java
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
     if (BuildConfig.DEBUG) {
         builder.addInterceptor(new OkHttpAnalyzerInterceptor());
     }   
    OkHttpClient client = builder.build(); 
    Retrofit retrofit = new Retrofit.Builder()
                ......
                .client(client)
                .build();


###### Kotlin
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor( OkHttpAnalyzerInterceptor() )
    }    
    val client = builder.build()
    val retrofit = Retrofit.Builder()
            ......
            .client(client)
            .build()

## Stargazers over time

[![Stargazers over time](https://starchart.cc/bytebeats/OkHttpAnalyzer.svg)](https://starchart.cc/bytebeats/OkHttpAnalyzer)

## Github Stars Sparklines

[![Sparkline](https://stars.medv.io/bytebeats/OkHttpAnalyzer.svg)](https://stars.medv.io/bytebeats/OkHttpAnalyzer)

## Contributors

[![Contributors over time](https://contributor-graph-api.apiseven.com/contributors-svg?chart=contributorOverTime&repo=bytebeats/OkHttpAnalyzer)](https://www.apiseven.com/en/contributor-graph?chart=contributorOverTime&repo=bytebeats/OkHttpAnalyzer)