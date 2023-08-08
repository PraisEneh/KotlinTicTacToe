package com.example.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity

var isCollinMode = false
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startGameButton: Button = findViewById(R.id.start_game_button)
        startGameButton.setOnClickListener {
            val intent = Intent(this, TicTacToeActivity::class.java)
            startActivity(intent)
        }

        val mediaPlayer = MediaPlayer.create(this, R.raw.just_for_giggles)

        var collinMode: Switch = findViewById(R.id.collin_mode_switch)
        collinMode.setOnCheckedChangeListener { _ , isChecked ->
            Toast.makeText(this, if(isChecked) "Collin Mode ON" else "Collin Mode OFF", Toast.LENGTH_SHORT).show()
            isCollinMode = isChecked
            mediaPlayer.start()
        }
    }
}
