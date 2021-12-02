package com.idme.mypurchase.common

object Constants {
    const val BASE_URL = "https://idme-takehome.proxy.beeceptor.com"
    const val LOG = "Log"

    enum class State{
        NORMAL,
        IN_PROGRESS,
        ERROR
    }
}