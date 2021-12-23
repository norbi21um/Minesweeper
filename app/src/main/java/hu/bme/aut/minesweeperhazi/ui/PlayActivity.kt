package hu.bme.aut.minesweeperhazi.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.minesweeperhazi.R
import hu.bme.aut.minesweeperhazi.adapter.PlayAdapter
import hu.bme.aut.minesweeperhazi.data.Cell
import hu.bme.aut.minesweeperhazi.data.MineGrid
import hu.bme.aut.minesweeperhazi.data.Player
import hu.bme.aut.minesweeperhazi.databinding.ActivityPlayBinding
import hu.bme.aut.minesweeperhazi.fragment.WinningDialogFragment
import hu.bme.aut.minesweeperhazi.viewmodel.PlayerViewModel
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random.Default.nextInt
import android.preference.PreferenceManager

class PlayActivity : AppCompatActivity(), PlayAdapter.PlayRecycleViewListener, WinningDialogFragment.WinnerNameDialogListener {


    private lateinit var binding: ActivityPlayBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var cellList: ArrayList<Cell>
    private lateinit var playAdapter: PlayAdapter
    private lateinit var flagButton:TextView
    private lateinit var smileyButton:TextView
    private lateinit var mineGrid:MineGrid
    private lateinit var chronometer: Chronometer
    private lateinit var playerViewModel: PlayerViewModel

    var isFlag:Boolean = false
    var numberOfBombs = 15
    var flagCnt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playerViewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        cellList = ArrayList()
        flagButton = binding.flagBtn
        smileyButton = binding.smileyBtn
        chronometer = binding.playTimer

        flagButton.setOnClickListener {
            if(isFlag){
                isFlag = false
                mineGrid.isFlag = false
                playAdapter.notifyDataSetChanged()
            } else if(!isFlag){
                isFlag = true
                mineGrid.isFlag = true
                playAdapter.notifyDataSetChanged()
            }
        }

        smileyButton.setOnClickListener {
            recreate()
        }
        mineGrid = MineGrid(cellList)

        startTimer()
        loadMode()
        boardGen()

        recyclerView = binding.activityMainGrid
        playAdapter = PlayAdapter(this, mineGrid, this, numberOfBombs)
        recyclerView.layoutManager = GridLayoutManager(this, 10)
        recyclerView.adapter = playAdapter

    }

    private fun boardGen(){
        for(n in 0..99){
            cellList.add(Cell(0,false, false))
        }
        var toMake: Int = numberOfBombs
        while(toMake > 0){
            var toBomb = (0 until 99).random()
            if (cellList[toBomb].state == 0){
                toMake--
                cellList[toBomb].state = 9
            }
        }

        for (i in 0..9) {
            for (j in 0..9) {
                if (cellList[i*10 +j].state != 9) {
                    var bombcounter: Int = 0
                    for (k in -1..1) {
                        for (l in -1..1) {
                            if (inBoard(i + k, j + l)) {
                                if (k == 0 && l == 0) {
                                } else if (cellList[(k + i) * 10 + (j + l)].state == 9) {
                                    bombcounter+=1
                                }
                            }
                        }
                    }
                    cellList[i*10 + j].state = bombcounter
                }
            }
        }
        mineGrid = MineGrid(cellList)
    }

    private fun inBoard(x: Int, y: Int): Boolean{
        if (x > 9 || y > 9 || y < 0 || x < 0)
            return false
        return true
    }


    private fun loadMode(){
        val sharedPreferences = getSharedPreferences("valami", Context.MODE_PRIVATE)
        val savedString = sharedPreferences.getString("STRING_KEY", null)

        binding.displayMode.text = savedString
        if(savedString.equals("easy")){
            numberOfBombs = 10
            binding.flagsleft.text = numberOfBombs.toString()
        } else if(savedString.equals("medium")){
            numberOfBombs = 20
            binding.flagsleft.text = numberOfBombs.toString()
        } else if(savedString.equals("hard")){
            numberOfBombs  = 30
            binding.flagsleft.text = numberOfBombs.toString()
        }
    }

    private fun startTimer(){
        chronometer.base = SystemClock.elapsedRealtime()
        //mineGrid.startTimer = SystemClock.elapsedRealtime()
        chronometer.start()
    }

    override fun gameOver(){
        chronometer.stop()
        for (item in cellList){
            item.isOpened = true
        }
        playAdapter.notifyDataSetChanged()
        //Toast.makeText(this, "ALSDJLASDJLASDL", Toast.LENGTH_LONG).show()
        binding.gameOver.visibility= View.VISIBLE

        mineGrid.timer = SystemClock.elapsedRealtime() - chronometer.base
        chronometer.stop()
    }


    override fun itemClicked(position: Int) {
        recurseReveal(position)
    }

    private fun recurseReveal(position: Int){
        var x: Int = position % 10
        var y: Int = position / 10
        if (!inBoard(x, y)) return
        mineGrid.cells[position].isOpened = true
        for (i in -1..1){
            for (j in -1..1){
                if (inBoard(x+i, y +j) && !mineGrid.cells[(x+i)+10 * (y + j)].isOpened && 9 != mineGrid.cells[(x+i)+10 * (y + j)].state){
                    mineGrid.cells[(x+i)+10 * (y + j)].isOpened = true
                    if(mineGrid.cells[(x+i)+10 * (y + j)].state == 0){
                        recurseReveal((x+i)+10 * (y + j))

                    }
                }
            }
        }
        playAdapter.notifyDataSetChanged()
    }

    override fun flagClicked() {
        flagCnt--
        binding.flagsleft.text = (numberOfBombs + flagCnt).toString()
    }

    override fun gameWon() {
        WinningDialogFragment().show(
        supportFragmentManager,
        WinningDialogFragment.TAG)
        chronometer.stop()
        mineGrid.timer = SystemClock.elapsedRealtime() - chronometer.base
    }

    override fun onPlayerItemCreated(name: String) {
        if(name.length != 0){
            var score = mineGrid.getScore(numberOfBombs)
            System.out.println(score)
            playerViewModel.addPlayer(Player((0..1000).random(), name, score))
        }
    }
}