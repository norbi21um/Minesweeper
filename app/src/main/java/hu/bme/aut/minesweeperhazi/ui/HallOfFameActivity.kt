package hu.bme.aut.minesweeperhazi.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.minesweeperhazi.adapter.ScoreboardAdapter
import hu.bme.aut.minesweeperhazi.data.Player
import hu.bme.aut.minesweeperhazi.databinding.ActivityHalloffamesBinding
import hu.bme.aut.minesweeperhazi.viewmodel.PlayerViewModel

class HallOfFameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHalloffamesBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var playerList: ArrayList<Player>
    private lateinit var scoreboardAdapter: ScoreboardAdapter
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalloffamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteBtn.setOnClickListener {
            playerViewModel.deleteAllPlayers()
            scoreboardAdapter.notifyDataSetChanged()
        }

        playerList = ArrayList()

        recyclerView = binding.RecyclerViewScoreboard
        scoreboardAdapter = ScoreboardAdapter(this, playerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = scoreboardAdapter

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        playerViewModel.readAllData.observe(this, Observer { player ->
            scoreboardAdapter.setData(player)
            playerList= player as ArrayList<Player>
        })

    }
}