package santa.server

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import jdk.nashorn.internal.objects.NativeObject.keys
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue


class ApplicationTest {
    val map = listOf<String>("Cloé", "Nina", "Célia", "Loena", "Elian")
    val formatter = Json { prettyPrint = false }


    @Test
    fun testRoot() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/test").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Hello, world!", response.content)
            }
        }
    }

    @Test
    fun testCreate() {
        withTestApplication(Application::module) {
            with(handleRequest(HttpMethod.Post, "/") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                val body = formatter.encodeToString(listOf<String>("Cloé", "Nina", "Célia", "Loena", "Elian"))
                setBody(body)
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(response.content)
                assertTrue( response.content!!.contains("id"))
            }
        }
    }


}
