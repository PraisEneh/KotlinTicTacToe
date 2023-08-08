package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity


class TicTacToeActivity : AppCompatActivity() {
    private var currentTurn = 'X'
    private var board = Array(3) { CharArray(3) }
    private var winner = '-'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)

        val mainMenuButton = findViewById<Button>(R.id.mainMenuButton)
        mainMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            isCollinMode = false
            finish()
        }


        val hello = MediaPlayer.create(this, R.raw.hello)
        val logCatfish = MediaPlayer.create(this, R.raw.log_catfish)

        // Initialize game board
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = ' '
            }
        }

        val buttons = arrayOf(
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9))

        for (i in buttons.indices) {
            buttons[i].setOnClickListener {
                if ((it as Button).text == "-" && !isGameOver()) {
                    it.text = currentTurn.toString()
                    board[i / 3][i % 3] = currentTurn
                    if (currentTurn == 'X') {
                        currentTurn = 'O'
                        it.background = resources.getDrawable(R.drawable.button_border_x)
                        if(isCollinMode) {
                            it.foreground = resources.getDrawable(R.drawable.catfish)
                            logCatfish.start()
                        }
                    } else {
                        currentTurn = 'X'
                        it.background = resources.getDrawable(R.drawable.button_border_o)
                        if (isCollinMode) {
                            it.foreground = resources.getDrawable(R.drawable.collin)
                            hello.start()
                        }
                    }
                }
                checkForWin(buttons)
            }
        }
    }


    private fun checkForWin(buttons: Array<Button>) {
        val states = mutableMapOf<String, String>()
        for (i in buttons.indices) {
            Log.d("Button text", buttons[i].text.toString())
            Log.d("Button id", buttons[i].id.toString())
            Log.d("Button name", resources.getResourceEntryName(buttons[i].id))
            states[resources.getResourceEntryName(buttons[i].id)] = buttons[i].text.toString()

        }
        if (isWinner(states)){
            if (isGameOver()) {resetBoard(buttons)}
        }else if(winner == 'T'){
            if (isGameOver()) {resetBoard(buttons)}
        }
    }

    private fun isWinner(states: Map<String, String>): Boolean {
        if (states["button1"]  == "X" && states["button2"]  == "X" && states["button3"] == "X") {
            winner = 'X'
        } else if (states["button1"]  == "X" && states["button4"]  == "X" && states["button7"] == "X") {
            winner = 'X'
        } else if (states["button1"]  == "X" && states["button5"]  == "X" && states["button9"] == "X") {
            winner = 'X'
        } else if (states["button2"]  == "X" && states["button5"]  == "X" && states["button8"] == "X") {
            winner = 'X'
        } else if (states["button3"]  == "X" && states["button5"]  == "X" && states["button7"] == "X") {
            winner = 'X'
        } else if (states["button3"]  == "X" && states["button6"]  == "X" && states["button9"] == "X") {
            winner = 'X'
        } else if (states["button4"]  == "X" && states["button5"]  == "X" && states["button6"] == "X") {
            winner = 'X'
        } else if (states["button7"]  == "X" && states["button8"]  == "X" && states["button9"] == "X") {
            winner = 'X'
        }

        if (states["button1"]  == "O" && states["button2"]  == "O" && states["button3"] == "O") {
            winner = 'O'
        } else if (states["button1"]  == "O" && states["button4"]  == "O" && states["button7"] == "O") {
            winner = 'O'
        } else if (states["button1"]  == "O" && states["button5"]  == "O" && states["button9"] == "O") {
            winner = 'O'
        } else if (states["button2"]  == "O" && states["button5"]  == "O" && states["button8"] == "O") {
            winner = 'O'
        } else if (states["button3"]  == "O" && states["button5"]  == "O" && states["button7"] == "O") {
            winner = 'O'
        } else if (states["button3"]  == "O" && states["button6"]  == "O" && states["button9"] == "O") {
            winner = 'O'
        } else if (states["button4"]  == "O" && states["button5"]  == "O" && states["button6"] == "O") {
            winner = 'O'
        } else if (states["button7"]  == "O" && states["button8"]  == "O" && states["button9"] == "O") {
            winner = 'O'
        }
        val isTie = states.values.all { it == "X" || it == "O" }
        if (winner == 'O' || winner == 'X'){
            return true
        } else if (isTie) {
            winner = 'T'
            return false
        }
        return false
    }

    private fun isGameOver(): Boolean {
        // Check if the game is over (win or tie)
        when (winner) {
            'X', 'O' -> {
                val intent = Intent(this, EndGameActivity::class.java).apply {
                    if(isCollinMode) {
                        if(winner == 'X') {
                            putExtra("winner", "Catfish")
                        }
                        else {
                            putExtra("winner", "Collin")
                        }
                    }
                    else { putExtra("winner", winner.toString()) }
                }
                startActivity(intent)
                return true
            }
            'T' -> {
                val intent = Intent(this, EndGameActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return false
    }

    private fun resetBoard(buttons: Array<Button>) {
        // Reset the game board
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = ' '
            }
        }

        for (button in buttons) {
            button.text = "-"
        }

        currentTurn = 'X'
        winner = '-'
    }

}
