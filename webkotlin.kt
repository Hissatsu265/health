

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(DefaultHeaders)
        install(ContentNegotiation) { jackson {} }
        install(StatusPages) {
            exception<Throwable> { cause ->
                call.respond(HttpStatusCode.InternalServerError, mapOf("error" to cause.localizedMessage))
            }
        }
        routing {
            get("/") {
                call.respond(mapOf("message" to "Welcome to Kotlin Web API!"))
            }
            get("/hello") {
                call.respond(mapOf("greeting" to "Hello, World!"))
            }
        }
    }.start(wait = true)
}
