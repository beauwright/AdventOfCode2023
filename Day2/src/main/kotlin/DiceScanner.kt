import java.io.File

class DiceScanner(private val file: String) {

    private val lines: MutableCollection<String> = ArrayList()
    private var linePos : Int = 0
    val games: MutableCollection<Game> = ArrayList()

    private fun readFile() {
        val bufferedReader = File(file).bufferedReader()

        bufferedReader.useLines { linesSequence ->
            linesSequence.forEach {
                lines.add(it)
            }
        }
    }
    fun scanLines() {
        readFile()
        for (line in lines) {
            // Chop off "Game " by starting at 5th char
            linePos = 5

            // Get game ID
            val gameID : Int = extractInt(line)
            val game = Game(gameID)

            while (linePos < line.length) {
                if (line[linePos] == ':') {
                    // Skip over ":"
                    linePos ++
                } else if (line[linePos] == ',') {
                    // Skip over ","
                    linePos++
                } else if (line[linePos] == ';') {
                    // Skip over ","
                    linePos++
                } else if (line[linePos].isWhitespace()) {
                    // Skip over whitespace
                    linePos++
                } else {
                    extractDice(line, game)
                }
            }
            games.add(game)
        }
    }

    private fun extractInt(line: String) : Int {
        var numberString = ""
        while(linePos < line.length && line[linePos].isDigit()) {
            numberString += (line[linePos])
            linePos++
        }
        var number = 0
        try {
            number = numberString.toInt() // Convert to Int
        } catch (e: NumberFormatException) {
            println("Invalid game number format in line: $line")
        }

        return number
    }

    private fun extractString(line: String) : String {
        var string = ""
        while(linePos < line.length && !line[linePos].isDigit()
            && !line[linePos].isWhitespace()
            && line[linePos] != ','
            && line[linePos] != ';'
        ) {
            string += (line[linePos])
            linePos++
        }
        return string
    }
    private fun extractDice(line: String, game: Game) {
        val numberOfDice = extractInt(line)
        // skip whitespace
        while(linePos < line.length && line[linePos].isWhitespace()) {
            linePos++
        }
        when (val diceColor = extractString(line)) {
            "red" -> {
                if(game.redDice.number < numberOfDice) {
                    game.redDice.number = numberOfDice
                }
            }
            "blue" -> {
                if(game.blueDice.number < numberOfDice) {
                    game.blueDice.number = numberOfDice
                }
            }
            "green" -> {
                if(game.greenDice.number < numberOfDice) {
                    game.greenDice.number = numberOfDice
                }
            }
            else -> {
                println("$diceColor is an invalid dice color")
            }
        }
    }
}