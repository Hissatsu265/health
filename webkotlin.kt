
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
// jsfukDHFKU
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
            get("/hi") {
                call.respond(mapOf("greeting" to "Hi, World!"))
            }
        }
    }.start(wait = true)
}
