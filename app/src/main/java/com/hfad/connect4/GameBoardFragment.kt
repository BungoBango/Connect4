//Evan Quinn
//Four In A Row
//2/25/24

package com.hfad.connect4

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import GameConstants.BLUE_WON
import GameConstants.RED_WON
import GameConstants.TIE
import android.os.Bundle
import androidx.navigation.fragment.navArgs


class GameBoardFragment : Fragment() {

    private lateinit var resetButton : Button
    private lateinit var gridLayout : GridLayout
    private val args: GameBoardFragmentArgs by navArgs()

    private lateinit var playerText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game_board, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetButton = view.findViewById(R.id.reset_button)
        resetButton.isEnabled = false
        gridLayout = view.findViewById(R.id.grid_layout)
        val name = args.playerName
        val welcomeMessage = view.context.getString(R.string.enjoy_message, name)
        val welcomeText : TextView = view.findViewById(R.id.welcome_message)
        welcomeText.text = welcomeMessage
        playerText = view.findViewById(R.id.turn_view)
    }


    //when the game ends, tie or player winner, the reset button can be clicked
    fun onGameStateChanged(gameState : Int) {
        if(gameState == TIE || gameState == RED_WON || gameState == BLUE_WON) {
            resetButton.isEnabled = true
        }
        else resetButton.isEnabled = false


        //if computer wins
         if (gameState == RED_WON) {
            playerText.text = context?.getString(R.string.computer_won)
        }
         //display player wins
        else if(gameState == BLUE_WON) {
            playerText.text = context?.getString(R.string.player_won)
        }
         // tie
        else if (gameState == TIE) {
            playerText.text = context?.getString(R.string.tie_game)
        }
    }

    fun playerTurn() {
        playerText.text = context?.getString(R.string.player_turn)
    }

    fun resetButtonClicked() {
        for (counter in 0..35) {
            val button = gridLayout.getChildAt(counter) as ImageButton
            button.isClickable = true
            button.setImageResource(R.drawable.empycell)


        }
        resetButton.isEnabled = false
        playerText.text = context?.getString(R.string.player_turn)
    }

    fun computerTurn() {
        playerText.text = context?.getString(R.string.player_turn)
    }
}