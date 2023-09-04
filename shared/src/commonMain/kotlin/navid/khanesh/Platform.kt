package navid.khanesh

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform