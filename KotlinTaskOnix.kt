import kotlin.random.Random

/**
 * A simple console-based "Guess the Number" game that allows two players to take turns guessing a secret number.
 *
 * In this game:
 * - One player chooses a secret number between 0 and 100.
 * - The other player (computer or human) attempts to guess the number.
 * - The computer uses a binary search strategy to guess the number efficiently.
 * - Feedback is provided after each guess to indicate whether the guess was too high, too low, or correct.
 * - Each player is allowed a maximum of 5 guesses per round.
 *
 * **Obligatory Cut Corners and Simplifications:**
 * - For demonstration purposes, the human player’s secret number is randomly chosen in the `chooseSecretNumber` method.
 *   In a full implementation, it would be possible to add prompt ability for input.
 * - Human guesses are passed as program arguments in the Kotlin Playground, limiting interactivity.
 * - The game consists of two rounds, switching roles between players.
 */
class Player(val name: String) {
    fun chooseSecretNumber(): Int {
        // For simplicity, the player chooses a random number from 0 to 100
        return Random.nextInt(0, 101) // In a real scenario, you would ask for input
    }
}

class Game(private val humanGuesses: List<Int>) {
    private val humanPlayer = Player("Human Player")
    private val computerPlayer = Player("Computer Player")
    private var currentPlayer: Player = humanPlayer
    private var otherPlayer: Player = computerPlayer

    // Computer guessing range
    private var minGuess = 0
    private var maxGuess = 100

    /**
     * Starts the game, allowing players to take turns guessing the secret number.
     */
    fun start() {
        println("Welcome to the Guess the Number Game!")

        repeat(2) { round ->
            println("\nRound ${round + 1}: It's ${currentPlayer.name}'s turn to choose a secret number.")

            val secretNumber = currentPlayer.chooseSecretNumber() // The current player chooses the secret number
            println("${currentPlayer.name} has chosen a secret number.")

            playRound(secretNumber)

            // Swap players for the next round
            currentPlayer = if (currentPlayer === humanPlayer) computerPlayer else humanPlayer
            otherPlayer = if (otherPlayer === humanPlayer) computerPlayer else humanPlayer

            // Reset the computer's guessing range for the next round
            minGuess = 0
            maxGuess = 100
        }
    }

    /**
     * Plays a round where the other player attempts to guess the secret number.
     *
     * @param secretNumber The secret number chosen by the current player.
     */
    private fun playRound(secretNumber: Int) {
        var guessedCorrectly = false
        val maxGuesses = 5 // Maximum of 5 guesses
        var currentGuessCount = 0

        while (!guessedCorrectly && currentGuessCount < maxGuesses) {
            val guess = if (otherPlayer === computerPlayer) {
                // Computer makes a guess using binary search strategy
                makeComputerGuess()
            } else {
                // Human makes a guess from the provided list
                if (humanGuesses.size > currentGuessCount) {
                    val guess = humanGuesses[currentGuessCount]
                    println("${otherPlayer.name} guessed: $guess")
                    guess
                } else {
                    println("${otherPlayer.name} has no more guesses left!")
                    break
                }
            }

            guessedCorrectly = checkGuess(guess, secretNumber)
            currentGuessCount++
        }

        if (!guessedCorrectly) {
            println("${otherPlayer.name} exceeded the maximum guesses of $maxGuesses. The number was $secretNumber.")
        }
    }

    /**
     * Makes a computer guess using the binary search strategy.
     *
     * @return The guessed number.
     */
    private fun makeComputerGuess(): Int {
        // Use binary search to guess
        val guess = (minGuess + maxGuess) / 2
        println("${computerPlayer.name} guessed: $guess")
        return guess
    }

    /**
     * Checks if the guess is correct, too high, or too low.
     *
     * @param guess The guessed number.
     * @param secretNumber The secret number to compare against.
     * @return True if the guess is correct; otherwise, false.
     */
    private fun checkGuess(guess: Int, secretNumber: Int): Boolean {
        return when {
            guess == secretNumber -> {
                println("${otherPlayer.name} guessed it right! The number was $secretNumber.")
                true
            }
            guess < secretNumber -> {
                println("Too low!")
                if (otherPlayer === computerPlayer) {
                    // Adjust the guessing range for the computer
                    minGuess = guess + 1
                }
                false
            }
            else -> {
                println("Too high!")
                if (otherPlayer === computerPlayer) {
                    // Adjust the guessing range for the computer
                    maxGuess = guess - 1
                }
                false
            }
        }
    }
}

/**
 * The entry point of the game.
 *
 * @param args The command-line arguments representing the list of human guesses.
 */
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide a list of human guesses as arguments.")
        return
    }

    // Convert command-line arguments to a list of integers for human guesses
    val humanGuesses = args.map { it.toInt() }

    val game = Game(humanGuesses)
    game.start()
}
