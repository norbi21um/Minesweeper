package hu.bme.aut.minesweeperhazi.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "player_table")
data class Player(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    var score:Int
)