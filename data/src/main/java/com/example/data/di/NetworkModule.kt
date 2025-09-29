package com.example.data.di

import com.example.data.BuildConfig.IS_DEV

const val DEV_API_URL = "http://10.0.2.2:8080/"
const val PROD_API_URL = "https://blindtrueordareserver-production.up.railway.app/"

val API_URL = if (IS_DEV) DEV_API_URL else PROD_API_URL

const val DEV_WEB_SOCKET_URL = "ws://10.0.2.2:8080/room?roomId="
const val PROD_WEB_SOCKET_URL = "wss://blindtrueordareserver-production.up.railway.app/room?roomId="

val WEB_SOCKET_URL = if (IS_DEV) DEV_WEB_SOCKET_URL else PROD_WEB_SOCKET_URL