package hu.bme.aut.minesweeperhazi.repository

import androidx.lifecycle.LiveData
import hu.bme.aut.minesweeperhazi.data.Player
import hu.bme.aut.minesweeperhazi.data.PlayerDao

class PlayerRepository(private val userDao: PlayerDao) {

    val readAllData: LiveData<List<Player>> = userDao.readAllData()

    suspend fun addPlayer(player: Player){
        userDao.addPlayer(player)
    }

    suspend fun updatePlayer(player: Player){
        userDao.updatePlayer(player)
    }

    suspend fun deletePlayer(player: Player){
        userDao.deletePlayer(player)
    }

    suspend fun deleteAllPlayers(){
        userDao.deleteAllPlayers()
    }

}