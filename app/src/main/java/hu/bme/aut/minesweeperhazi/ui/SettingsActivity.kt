package hu.bme.aut.minesweeperhazi.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hu.bme.aut.minesweeperhazi.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var appSettingsPrefs: SharedPreferences
    private lateinit var sharedPrefsEdit: SharedPreferences.Editor
    var currentMode:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()

        binding.button.setOnClickListener {
            saveData("easy")
        }

        binding.button2.setOnClickListener {
            saveData("medium")
        }

        binding.button3.setOnClickListener {
            saveData("hard")
        }


    }

    private fun saveData(string : String){
        var savedString:String = string

        binding.currentMode.text = savedString
        val sharedPreferences = getSharedPreferences("valami", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("STRING_KEY", savedString)
        }.apply()

        Toast.makeText(this, "Data Saved", Toast.LENGTH_LONG).show()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences("valami", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)

        binding.currentMode.text = savedString
    }



}