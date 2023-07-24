package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EndGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_game_layout)

        val endGameMessage: TextView = findViewById(R.id.end_game_message)
        val newGameButton: Button = findViewById(R.id.new_game_button)
        val mainMenuButton: Button = findViewById(R.id.main_menu_button)

        val winner = intent.getStringExtra("winner")
        if (winner != null) {
            endGameMessage.text = "$winner won"
        } else {
            endGameMessage.text = "It's a tie"
        }

        newGameButton.setOnClickListener {
            val intent = Intent(this, TicTacToeActivity::class.java)
            startActivity(intent)
        }

        mainMenuButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
