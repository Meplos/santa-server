package santa.server.domain.models

import org.junit.Test
import kotlin.test.*

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
        val map = mapOf("Cloé" to "Célia", "Célia" to "Nina", "Nina" to "Elian", "Elian" to "Cloé")
        val expected = map.keys
        val party = Party(map)
        assertNotNull(party.getParticipants())
        assertEquals(party.getParticipants().size, expected.size)
        assert(party.getParticipants().containsAll(expected))
    }

}