import org.junit.jupiter.api.Test

import Result.DRAW
import kotlin.test.assertEquals

internal class MainKtTest {

    @Test
    fun thatAllRoundsCounted() {
        val rounds = 100
        var draws = 0
        var wins = 0

        val game = generateSequence { createRound() }
            .take(rounds)
            .map { round -> evalRound(round) }
            .toList()

        game.forEach{ o -> if (o.result == DRAW) draws++}
        game
            .filter { o -> o.winner != null }
            .groupBy { it.winner!!.name }
            .forEach{ winner -> wins += winner.value.size }

        assertEquals(rounds, draws + wins)
    }
    
    
}