import Action.*
import Result.*
import kotlin.random.Random

fun main() {
    val rounds = 100
    var draws = 0

    val game = generateSequence { createRound() }
        .take(rounds)
        .map { round -> evalRound(round) }
        .toList()

    game.forEach{ o -> if (o.result == DRAW) draws++}
    println("Draws: $draws")

    game
        .filter { o -> o.winner != null }
        .groupBy { it.winner!!.name }
        .forEach{ winner -> println(winner.key + ": " + winner.value.size + " wins" ) }
}


fun createRound(): Round {
    val playerOne = Player("RockPlayer", ROCK)

    val randomAction = { Action.values()[Random.Default.nextInt(3)]}
    val playerTwo = Player("RandomPlayer", randomAction())

    return Round(playerOne, playerTwo)
}

fun evalRound(round: Round): Outcome {
    val actionPlayerOne = round.playerOne.action;
    val actionPlayerTwo = round.playerTwo.action;

    if (actionPlayerOne == actionPlayerTwo) {
        return Outcome(DRAW)
    }

    return when (actionPlayerOne) {
        ROCK -> {
            if (actionPlayerTwo == PAPER) {
                Outcome(WIN, round.playerTwo)
            } else {
                Outcome(WIN, round.playerOne)
            }
        }
        PAPER -> {
            if (actionPlayerTwo == ROCK) {
                Outcome(WIN, round.playerOne)
            } else {
                Outcome(WIN, round.playerTwo)
            }
        }
        SCISSORS -> {
            if (actionPlayerTwo == ROCK) {
                Outcome(WIN, round.playerTwo)
            } else {
                Outcome(WIN, round.playerOne)
            }
        }
    }
}


/*
 Even though I can see the appeal of a "prettier" code (@evalRound2 and @evalRound3) I still decided to go for the
 "uglier" but more resilient @evalRound.
 Both functions below (@evalRound2 and @evalRound3) work from the poi of a specific player (either RockPlayer or RandomPlayer), however:
  1. This makes the whole logic less robust, when it comes to counting the wins, losses and draws for ALL players.
  Yes, you could just subtract the number of draws and losses of the RockPlayer from the number of rounds to get the
  wins of the RandomPlayer, but this just moves the "ugly" code to the place where evalRound2 is called.
  You also need to assume that round - draws - RockPlayer.losses = RandomPlayer.wins and that in this case
  RandomPlayer.wins does not contain anything else.
  2. @evalRound2 is especially vulnerable to changes in @Action since it depends on the ordinal value of the enums.
  All it takes to break this logic is e.g. a simple auto formatter that sorts enums in alphabetical order.

 Other than that, the rules of Rock-Paper-Scissors have not changed in decades, so I don't expect anyone touching the
 code in the foreseeable future. This is also why I would go with @evalRound - something that just works.

 Also "ugly" code is not necessarily bad code. In this case I find it to be readable and maintainable. Just a few lines
 longer than evalRound2 and evalRound3. The memory should be able to handle it ;)
 */
//fun evalRound2(round: Round): Outcome {
//    // from viewpoint of playerOne
//    val lookupTable = arrayOf(
//        // playerOne ROCK
//        // playerTwo:  ROCK         PAPER        SCISSORS
//        arrayOf(DRAW, LOSE, WIN),
//        // playerOne PAPER
//        // playerTwo:  ROCK        PAPER        SCISSORS
//        arrayOf(WIN, DRAW, LOSE),
//        // playerOne SCISSORS
//        // playerTwo:  ROCK         PAPER       SCISSORS
//        arrayOf(LOSE, WIN, DRAW)
//    );
//
//    val actionPlayerOne = round.playerOne.action;
//    val actionPlayerTwo = round.playerTwo.action;
//
//    val playerOneResult =  lookupTable[actionPlayerOne.ordinal][actionPlayerTwo.ordinal];
//
//    return if (playerOneResult == DRAW) {
//        Outcome(playerOneResult)
//    } else {
//        Outcome(playerOneResult, round.playerOne)
//    }
//}


//fun evalRound3(round: Round): Outcome {
//    val actionPlayerOne = round.playerOne.action;
//    val actionPlayerTwo = round.playerTwo.action;
//
//    if (actionPlayerOne == actionPlayerTwo) {
//        return Outcome(DRAW)
//    }
//
//    return when (Pair(actionPlayerOne, actionPlayerTwo)) {
//        Pair(ROCK, ROCK) -> Outcome(DRAW)
//        Pair(ROCK, PAPER) -> Outcome(LOSE, round.playerOne)
//        Pair(ROCK, SCISSORS) -> Outcome(WIN, round.playerOne)
//        Pair(PAPER, ROCK) -> Outcome(WIN, round.playerOne)
//        Pair(PAPER, PAPER) -> Outcome(DRAW)
//        Pair(PAPER, SCISSORS) -> Outcome(LOSE, round.playerOne)
//        Pair(SCISSORS, ROCK) -> Outcome(LOSE, round.playerOne)
//        Pair(SCISSORS, PAPER) -> Outcome(WIN, round.playerOne)
//        Pair(SCISSORS, SCISSORS) -> Outcome(DRAW)
//        else -> Outcome(INVALID)
//    }
//}