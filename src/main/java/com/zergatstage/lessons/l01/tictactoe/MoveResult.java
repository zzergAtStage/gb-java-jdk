package com.zergatstage.lessons.l01.tictactoe;

// Assume you have a method to check if the game is over and to evaluate the current state of the board.

// Define a simple class to represent the result of the Minimax algorithm.
class MoveResult {
    int score;
    int row;
    int col;

    MoveResult(int score) {
        this.score = score;
    }
}

