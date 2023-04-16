import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EvalRaoundTest {

    @Test
    fun thatGameWorks() {
        var playerOne = Player("PlayerOne", Action.ROCK);
        var playerTwo = Player("PlayerTwo", Action.ROCK);
        var outcome = Outcome(Result.DRAW);

        var round = Round(playerOne, playerTwo);
        var evaluatedRound = evalRound(round)
        assertEquals(null, evaluatedRound.winner)
        assertEquals(Result.DRAW, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.ROCK);
        playerTwo = Player("PlayerTwo", Action.PAPER);
        outcome = Outcome(Result.WIN, playerTwo);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(playerTwo, evaluatedRound.winner)
        assertEquals(Result.WIN, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.ROCK);
        playerTwo = Player("PlayerTwo", Action.SCISSORS);
        outcome = Outcome(Result.WIN, playerOne);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(playerOne, evaluatedRound.winner)
        assertEquals(Result.WIN, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.PAPER);
        playerTwo = Player("PlayerTwo", Action.ROCK);
        outcome = Outcome(Result.WIN, playerOne);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(playerOne, evaluatedRound.winner)
        assertEquals(Result.WIN, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.PAPER);
        playerTwo = Player("PlayerTwo", Action.PAPER);
        outcome = Outcome(Result.DRAW);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(null, evaluatedRound.winner)
        assertEquals(Result.DRAW, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.PAPER);
        playerTwo = Player("PlayerTwo", Action.SCISSORS);
        outcome = Outcome(Result.WIN, playerTwo);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(playerTwo, evaluatedRound.winner)
        assertEquals(Result.WIN, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.SCISSORS);
        playerTwo = Player("PlayerTwo", Action.ROCK);
        outcome = Outcome(Result.WIN, playerTwo);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(playerTwo, evaluatedRound.winner)
        assertEquals(Result.WIN, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.SCISSORS);
        playerTwo = Player("PlayerTwo", Action.PAPER);
        outcome = Outcome(Result.WIN, playerOne);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(playerOne, evaluatedRound.winner)
        assertEquals(Result.WIN, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)

        // ------------------------------------------------------------
        playerOne = Player("PlayerOne", Action.SCISSORS);
        playerTwo = Player("PlayerTwo", Action.SCISSORS);
        outcome = Outcome(Result.DRAW);

        round = Round(playerOne, playerTwo);
        evaluatedRound = evalRound(round)
        assertEquals(null, evaluatedRound.winner)
        assertEquals(Result.DRAW, evaluatedRound.result)
        assertEquals(outcome, evaluatedRound)
    }
}