fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        val filePath = args[0]
        val scanner = DiceScanner(filePath)
        scanner.scanLines()
        val games = scanner.games
        solvePartOne(games)
        solvePartTwo(games)
    } else {
        println("Please provide the file path as a command line argument.")
    }
}

fun solvePartOne(games: MutableCollection<Game>) {
    var sumOfGameIDs = 0
    for (game in games) {
        if (game.redDice.number <= 12 && game.greenDice.number <= 13 && game.blueDice.number <= 14) {
            sumOfGameIDs += game.gameNumber
        }
    }
    println(sumOfGameIDs)
}

fun solvePartTwo(games: MutableCollection<Game>) {
    var sumOfMinPowers = 0
    for (game in games) {
        val minPower : Int = game.redDice.number * game.greenDice.number * game.blueDice.number
        sumOfMinPowers += minPower
    }
    println(sumOfMinPowers)
}