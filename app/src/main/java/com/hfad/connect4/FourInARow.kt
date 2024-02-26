//Evan Quinn
//Four In A Row
//2/25/24
import GameConstants.BLUE
import GameConstants.BLUE_WON
import GameConstants.COMPUTER_PLAYER
import GameConstants.EMPTY
import GameConstants.HUMAN_PLAYER
import GameConstants.PLAYING
import GameConstants.RED
import GameConstants.RED_WON
import GameConstants.TIE


class FourInARow

    : IGame {
    // sets array values to zero
    private val board = Array(GameConstants.ROWS) { IntArray(GameConstants.COLS){0} }



    override fun setMove(player: Int, location: Int) {

        var the_col: Int = location
        var the_row: Int = 0

        while (the_col >= 6) {
            the_col -= 6
            the_row++
        }//convert 0-35 to a space on the board
        val color = if ( player == HUMAN_PLAYER) {
            BLUE
        }
        else {
            RED
        }
        //sets the slot to the player
        board[the_row][the_col] = color
    }
    override fun clearBoard() {


        //This makes every slot empty
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                board[row][col] = EMPTY
            }
        }
    }

    //returns the first move for the computer
    override fun computerMove(): Int {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == EMPTY) {
                    return (row * 6) + col
                }
            }
        }
        return -1
    }

    override fun checkForWinner(): Int {


        var has_won: Boolean = false;
        for (current_player in HUMAN_PLAYER..COMPUTER_PLAYER) {
            val color = if (current_player == HUMAN_PLAYER) {
                BLUE
            }
            else {
                RED
            }
            // checks horizontally
            for (row in 0 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS - 3) {
                    if (board[row][col] == color &&
                        board[row][col + 1] == color &&
                        board[row][col + 2] == color &&
                        board[row][col + 3] == color
                    ) {
                        has_won = true
                    }
                }
            }
            // checks vertically
            for (row in 0 until GameConstants.ROWS - 3) {
                for (col in 0 until GameConstants.COLS) {
                    if (board[row][col] == color &&
                        board[row + 1][col] == color &&
                        board[row + 2][col] == color &&
                        board[row + 3][col] == color
                    ) {
                        has_won = true
                    }
                }
            }
            // checks left diagonally
            for (row in 3 until GameConstants.ROWS) {
                for (col in 0 until GameConstants.COLS - 3) {
                    if (board[row][col] == color &&
                        board[row - 1][col + 1] == color &&
                        board[row - 2][col + 2] == color &&
                        board[row - 3][col + 3] == color
                    ) {
                        has_won = true
                    }
                }
            }
            //checks right diagonally
            for (row in 0 until GameConstants.ROWS - 3) {
                for (col in 0 until GameConstants.COLS - 3) {
                    if (board[row][col] == color &&
                        board[row + 1][col + 1] == color &&
                        board[row + 2][col + 2] == color &&
                        board[row + 3][col + 3] == color
                    ) {
                        has_won = true
                    }
                }
            }
            if (has_won) {
                if (current_player == HUMAN_PLAYER) {
                    return BLUE_WON;
                }
                else {
                    return RED_WON
                }
            }
            if (isTie()) {
                return TIE
            }


        }
        return PLAYING
    }


    //if every slot is taken its a tie
    private fun isTie(): Boolean {
        for (row in 0 until GameConstants.ROWS) {
            for (col in 0 until GameConstants.COLS) {
                if (board[row][col] == EMPTY) {
                    return false
                }
            }
        }
        return true
    }
}