fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        val filePath = args[0]
        solvePartOne(filePath)
        solvePartTwo(filePath)
    } else {
        println("Please provide the file path as a command line argument.")
    }
}

fun solvePartOne(filePath: String) {
    val scanner = DiceScanner(filePath)
    scanner.scanLines()
    var sumOfGameIDs = 0
    for (game in scanner.games) {
        if (game.redDice.number <= 12 && game.greenDice.number <= 13 && game.blueDice.number <= 14) {
            sumOfGameIDs += game.gameNumber
        }
    }
    println(sumOfGameIDs)
}

fun solvePartTwo(filePath: String) {
    val scanner = DiceScanner(filePath)
    scanner.scanLines()
    var sumOfMinPowers = 0
    for (game in scanner.games) {
        val minPower : Int = game.redDice.number * game.greenDice.number * game.blueDice.number
        sumOfMinPowers += minPower
    }
    println(sumOfMinPowers)
}