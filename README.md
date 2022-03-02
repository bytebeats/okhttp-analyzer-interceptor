# okhttp-analyzer-interceptor

[![GitHub latest commit](https://badgen.net/github/last-commit/bytebeats/okhttp-analyzer-interceptor)](https://github.com/bytebeats/okhttp-analyzer-interceptor/commit/)
[![GitHub contributors](https://img.shields.io/github/contributors/bytebeats/okhttp-analyzer-interceptor.svg)](https://github.com/bytebeats/okhttp-analyzer-interceptor/graphs/contributors/)
[![GitHub issues](https://img.shields.io/github/issues/bytebeats/okhttp-analyzer-interceptor.svg)](https://github.com/bytebeats/okhttp-analyzer-interceptor/issues/)
[![Open Source? Yes!](https://badgen.net/badge/Open%20Source%20%3F/Yes%21/blue?icon=github)](https://github.com/bytebeats/okhttp-analyzer-interceptor/)
[![GitHub forks](https://img.shields.io/github/forks/bytebeats/okhttp-analyzer-interceptor.svg?style=social&label=Fork&maxAge=2592000)](https://github.com/bytebeats/okhttp-analyzer-interceptor/network/)
[![GitHub stars](https://img.shields.io/github/stars/bytebeats/okhttp-analyzer-interceptor.svg?style=social&label=Star&maxAge=2592000)](https://github.com/bytebeats/okhttp-analyzer-interceptor/stargazers/)
[![GitHub watchers](https://img.shields.io/github/watchers/bytebeats/okhttp-analyzer-interceptor.svg?style=social&label=Watch&maxAge=2592000)](https://github.com/bytebeats/okhttp-analyzer-interceptor/watchers/)

An intellij platform plugin. To analyze details of requests and responses from OkHttp.

## Tech stack and why
* [Kotlin](https://kotlinlang.org/) - Kotlin on Android is a “first-class” language and it has [a lot of benefits](https://developer.android.com/kotlin).
* [MVVM & MVI Architecture](https://developer.android.com/jetpack/guide) - Modern, maintainable, and Google suggested app architecture.
* [Coroutine](https://developer.android.com/kotlin/coroutines) + [Flow](https://developer.android.com/kotlin/flow) = [MVI](https://github.com/Kotlin-Android-Open-Source/MVI-Coroutines-Flow)
* [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client for Android and Java.
* [OkHttp](https://square.github.io/okhttp/) - Meticulous HTTP client for the JVM, Android, and GraalVM.
* [Moshi](https://github.com/square/moshi) - Modern JSON library for Android, Java and Kotlin. It makes it easy to parse JSON into Java and Kotlin classes.
* [Jetpack Components](https://developer.android.com/jetpack) - Compose, ViewModel and more.

## How to use

##### For OkHttp

###### Dependencies

1. add maven url in your root `build.gradle`:
```
    repositories {
       maven { url('https://repo1.maven.org/maven2/') }
               ...
    }
```

2. add dependency in your module `build.gradle`
```
dependencies {
    implementation "androidx.compose.ui:ui:$compose_version"

//    implementation project(':interceptors')
    implementation('io.github.bytebeats:okhttp-analyzer-interceptor:$LATEST_VERSION')//-n means okhttp major version. like 4 means 4.9.3, 3 means 3.14.9
}
```

Note: **`LATEST_VERSION`** : 0.1.3-4

###### Java
```
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
     if (BuildConfig.DEBUG) {
         builder.addInterceptor(new OkHttpAnalyzerInterceptor());
     }   
    OkHttpClient client = builder.build();
``` 

###### Kotlin
```
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(OkHttpAnalyzerInterceptor() )
    }    
    val client = builder.build()
```

##### For Retrofit
###### Java
```
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
     if (BuildConfig.DEBUG) {
         builder.addInterceptor(new OkHttpAnalyzerInterceptor());
     }   
    OkHttpClient client = builder.build(); 
    Retrofit retrofit = new Retrofit.Builder()
                ......
                .client(client)
                .build();
```


###### Kotlin
```
    val builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        builder.addInterceptor( OkHttpAnalyzerInterceptor() )
    }    
    val client = builder.build()
    val retrofit = Retrofit.Builder()
            ......
            .client(client)
            .build()
```

## Stargazers over time

[![Stargazers over time](https://starchart.cc/bytebeats/okhttp-analyzer-interceptor.svg)](https://starchart.cc/bytebeats/okhttp-analyzer-interceptor)

## Github Stars Sparklines

[![Sparkline](https://stars.medv.io/bytebeats/okhttp-analyzer-interceptor.svg)](https://stars.medv.io/bytebeats/okhttp-analyzer-interceptor)

## Contributors

[![Contributors over time](https://contributor-graph-api.apiseven.com/contributors-svg?chart=contributorOverTime&repo=bytebeats/okhttp-analyzer-interceptor)](https://www.apiseven.com/en/contributor-graph?chart=contributorOverTime&repo=bytebeats/okhttp-analyzer-interceptor)

## MIT License

    Copyright (c) 2021 Chen Pan

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
