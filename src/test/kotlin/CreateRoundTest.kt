import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class CreateRoundTest {

    @Test
    fun thatTwoPlayersExist() {
        val round = createRound()
        assertNotNull(round.playerOne)
        assertNotNull(round.playerTwo)
    }

    @Test
    fun thatBothPlayersHaveProperties() {
        val round = createRound()
        assertNotNull(round.playerOne.name)
        assertNotNull(round.playerTwo.name)
        assertNotNull(round.playerOne.action)
        assertNotNull(round.playerTwo.action)
    }

    @Test
    fun thatMinOnePlayerSelectedRock() {
        val round = createRound()
        val hasRockPlayer = round.playerOne.action == Action.ROCK || round.playerTwo.action == Action.ROCK
        assertTrue(hasRockPlayer)
    }

    @Test
    fun thatHasDifferentActions() {
        assertTrue(generateSequence { createRound() }
            .take(100)
            .flatMap { r -> sequenceOf(r.playerOne, r.playerTwo) }
            .groupBy{it.action}
            .size > 1)
    }
}