# Guess the Number Game

A simple console-based "Guess the Number" game developed in Kotlin. This game allows two players to take turns guessing a secret number, with one player being a human and the other a computer that uses a binary search strategy for guessing.

## Overview

- **Players**: 2 (1 Human, 1 Computer)
- **Secret Number Range**: 0 to 100
- **Maximum Guesses**: Each player has up to 5 guesses per round.

## Features

- Each player takes turns choosing a secret number.
- The other player attempts to guess the secret number.
- Feedback is provided after each guess, indicating whether it was too high, too low, or correct.
- The game consists of two rounds, switching roles between players.

## Requirements

- Kotlin Playground or any Kotlin-compatible environment.

## How to Run

1. **Using Kotlin Playground**:
   - Copy the Kotlin code from `Main.kt`.
   - Open [Kotlin Playground](https://play.kotlinlang.org/).
   - Set program arguments to provide human guesses (e.g., `50, 30, 70, 10, 90`).
   - Paste the code into the editor.
   - Click the "Run" button to start the game.

2. **Using an IDE (e.g., IntelliJ IDEA)**:
   - Create a new Kotlin project in your preferred IDE.
   - Create a new Kotlin file (e.g., `Main.kt`) and paste the provided code into it.
   - In the run configuration, specify program arguments as a list of integers representing human guesses (e.g., `50 30 70 10 90`).
   - Run the program to start the game.

## Notes

- The secret number chosen by the human player is simulated randomly for demonstration purposes.
- Human guesses must be provided as command-line arguments due to limitations in the interactive input of Kotlin Playground.
