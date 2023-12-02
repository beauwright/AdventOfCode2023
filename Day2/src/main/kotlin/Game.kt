data class Game(val gameNumber : Int,
                val redDice : Dice = Dice("red", 0),
                val greenDice : Dice = Dice("green", 0),
                val blueDice : Dice = Dice("blue", 0)
)