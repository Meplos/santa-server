package santa.server

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
    var name: String? = null
    var id: String? = null

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
    @kotlinx.serialization.ExperimentalSerializationApi
    fun testCreate() {
        withTestApplication(Application::module) {
            with(handleRequest(HttpMethod.Post, "/") {
                addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                val body = formatter.encodeToString(listOf<String>("Cloé", "Nina", "Célia", "Loena", "Elian"))
                setBody(body)
            }) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(response.content)
                assertTrue(response.content!!.contains("id"))
                val res = Gson().fromJson<Map<String, String>>(
                    response.content,
                    object : TypeToken<Map<String, String>>() {}.type
                )
                id = res["id"]
            }
        }
    }

    @Test
    fun testGetParticipant() {
        withTestApplication(Application::module) {
            with(handleRequest(HttpMethod.Get, "/$id") { }) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(response.content)
                val res = Gson().fromJson<Map<String, Set<String>>>(
                    response.content,
                    object : TypeToken<Map<String, Set<String>>>() {}.type
                )
                assertNotNull(res ["participants"])

                val participants = res["participants"]

                name = participants?.first()
                participants?.let { map.containsAll(it) }?.let { assert(it) }
            }
        }
    }

    @Test
    fun testGetSanta() {
        withTestApplication(Application::module) {
            with(handleRequest(HttpMethod.Get, "/$id/participant/$name") { }) {
                assertEquals(HttpStatusCode.OK, response.status())
                assertNotNull(response.content)
                val res = Gson().fromJson<Map<String, String>>(
                    response.content,
                    object : TypeToken<Map<String, String>>() {}.type
                )
                assertNotNull(res["name"])
                name = res["name"]
                assert(map.contains(name))
            }
        }
    }


}


}
