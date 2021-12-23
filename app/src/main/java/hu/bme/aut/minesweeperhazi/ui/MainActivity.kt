package hu.bme.aut.minesweeperhazi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.minesweeperhazi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.playButton.setOnClickListener {
            val intent1 = Intent(this, PlayActivity::class.java)
            startActivity(intent1)
        }

        binding.settingsButton.setOnClickListener {
            val intent2 = Intent(this, SettingsActivity::class.java)
            startActivity(intent2)
        }

        binding.hallOfFameButton.setOnClickListener {
            val intent = Intent(this, HallOfFameActivity::class.java)
            startActivity(intent)
        }

    }
}