package hu.bme.aut.minesweeperhazi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hu.bme.aut.minesweeperhazi.data.Player
import hu.bme.aut.minesweeperhazi.data.PlayerDatabase
import hu.bme.aut.minesweeperhazi.repository.PlayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PlayerViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Player>>
    private val repository: PlayerRepository

    init {
        val userDao = PlayerDatabase.getDatabase(
            application
        ).playerDao()
        repository = PlayerRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addPlayer(player: Player){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }
    }

    fun updatePlayer(player: Player){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayer(player)
        }
    }

    fun deletePlayer(player: Player){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlayer(player)
        }
    }

    fun deleteAllPlayers(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPlayers()
        }
    }
}