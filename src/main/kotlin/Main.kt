fun main() {
    println("Round One:")
    val playerOne = Player("PlayerOne", Action.ROCK);
    val playerTwo = Player("PlayerTwo", Action.PAPER);
    var outcome = Outcome(Result.WIN, playerTwo);
    val roundOne = Round(playerOne, playerTwo, outcome);
    println(roundOne);

    println("Round Two:")
    playerOne.action = Action.SCISSORS;
    playerTwo.action = Action.SCISSORS;
    outcome = Outcome(Result.DRAW);
    val roundTwo = Round(playerOne, playerTwo, outcome);
    println(roundTwo);
}
