package com.bia.focadinha

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform