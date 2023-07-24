package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton: Button = findViewById(R.id.start_game_button)
        startGameButton.setOnClickListener {
            val intent = Intent(this, TicTacToeActivity::class.java)
            startActivity(intent)
        }
    }
}
