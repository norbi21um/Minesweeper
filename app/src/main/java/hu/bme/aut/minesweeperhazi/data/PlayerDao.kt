package hu.bme.aut.minesweeperhazi.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPlayer(user: Player): Unit

    @Update
    fun updatePlayer(user: Player): Unit

    @Delete
    fun deletePlayer(user: Player): Unit

    @Query("DELETE FROM player_table")
    fun deleteAllPlayers(): Unit

    @Query("SELECT * FROM player_table ORDER BY name ASC")
    fun readAllData(): LiveData<List<Player>>
}
