package santa.server.domain.models

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class PartyTest {
    @Test
    fun getSantaShouldPass() {
        val party = Party(mapOf("Cloé" to "Nina"))
        assertNotNull(party.getSantaFor("Cloé"))
        assertEquals(party.getSantaFor("Cloé"), "Nina")
        assertNotEquals(party.getSantaFor("Cloé"), "Célia")
    }

    @Test
    fun getParticipant() {
        val expected = listOf("Cloé", "Célia", "Nina", "Elian")
        val party = Party(mapOf("Cloé" to "Célia", "Célia" to "Nina", "Nina" to "Elian", "Elian" to "Cloé"))
     }

}