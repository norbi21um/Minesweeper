package hu.bme.aut.minesweeperhazi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.minesweeperhazi.R
import hu.bme.aut.minesweeperhazi.data.Player


class ScoreboardAdapter(val context: Context, var playerList:ArrayList<Player>): RecyclerView.Adapter<ScoreboardAdapter.ScoreboardViewHolder>() {


    inner class ScoreboardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var score: TextView
        var rank: TextView

        init {
            name = view.findViewById<TextView>(R.id.name_textview)
            score = view.findViewById<TextView>(R.id.score_textview)
            rank = view.findViewById<TextView>(R.id.rank_textview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_hall_of_fame, parent, false)
        return ScoreboardViewHolder(view)
    }


    override fun getItemCount(): Int {
        return playerList.size
    }

    fun setData(user: List<Player>){
        playerList = user as ArrayList<Player>
        playerList.sortBy { it.score }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ScoreboardViewHolder, position: Int) {
        val newList = playerList[position]
        holder.name.text = newList.name
        holder.score.text = newList.score.toString()
        holder.rank.text = (position+1).toString()

        holder.rank.setOnClickListener {

        }

    }
}