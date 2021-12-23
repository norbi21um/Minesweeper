package hu.bme.aut.minesweeperhazi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.minesweeperhazi.R
import hu.bme.aut.minesweeperhazi.data.Cell
import hu.bme.aut.minesweeperhazi.data.MineGrid


class PlayAdapter(val context: Context, var mineGrid: MineGrid, private val listener: PlayRecycleViewListener, private val numberOfBombs:Int): RecyclerView.Adapter<PlayAdapter.PlayViewHolder>() {

    var cellList:ArrayList<Cell> = mineGrid.cells


    interface PlayRecycleViewListener {
        fun gameOver()
        fun itemClicked(position: Int)
        fun flagClicked()
        fun gameWon()
    }

    inner class PlayViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var base_cell:ImageView
        var flag:ImageView
        var mine1: ImageView
        var mine2: ImageView
        var mine3: ImageView
        var mine4: ImageView
        var mine5: ImageView
        var mine6: ImageView
        var mine7: ImageView
        var mine8: ImageView
        var bomb: ImageView
        var no_value_mine: ImageView

        init {
            base_cell = view.findViewById<ImageView>(R.id.base_cell)
            flag = view.findViewById<ImageView>(R.id.flag)
            mine1 = view.findViewById<ImageView>(R.id.mine1)
            mine2 = view.findViewById<ImageView>(R.id.mine2)
            mine3 = view.findViewById<ImageView>(R.id.mine3)
            mine4 = view.findViewById<ImageView>(R.id.mine4)
            mine5 = view.findViewById<ImageView>(R.id.mine5)
            mine6 = view.findViewById<ImageView>(R.id.mine6)
            mine7 = view.findViewById<ImageView>(R.id.mine7)
            mine8 = view.findViewById<ImageView>(R.id.mine8)
            bomb = view.findViewById<ImageView>(R.id.bomb)
            no_value_mine = view.findViewById<ImageView>(R.id.no_value_mine)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)

        return PlayViewHolder(view)
    }


    override fun getItemCount(): Int {
        return cellList.size
    }

    fun setData(player: List<Cell>){
        cellList = player as ArrayList<Cell>
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: PlayViewHolder, position: Int) {
        val item = cellList[position]

        if(item.isOpened){
            holder.base_cell.visibility = View.GONE
            if (item.state == 1) {
                holder.mine1.visibility = View.VISIBLE
            } else if (item.state == 2) {
                holder.mine2.visibility = View.VISIBLE
            } else if (item.state == 3) {
                holder.mine3.visibility = View.VISIBLE
            } else if (item.state == 4) {
                holder.mine4.visibility = View.VISIBLE
            } else if (item.state == 5) {
                holder.mine5.visibility = View.VISIBLE
            } else if (item.state == 6) {
                holder.mine6.visibility = View.VISIBLE
            } else if (item.state == 7) {
                holder.mine7.visibility = View.VISIBLE
            } else if (item.state == 8) {
                holder.mine8.visibility = View.VISIBLE
            } else if (item.state == 9) {
                holder.bomb.visibility = View.VISIBLE
            }else if (item.state == 0) {
                holder.no_value_mine.visibility = View.VISIBLE
            }
            /**Itt lehet a flagek megmaradásának hibája*/
        } else if(item.isFlagged){
            holder.base_cell.visibility = View.GONE
            holder.flag.visibility = View.VISIBLE
        }


        holder.base_cell.setOnClickListener {

            if (!item.isOpened && !mineGrid.isFlag && !item.isFlagged){
                holder.base_cell.visibility = View.GONE
                item.isOpened = true
                if (item.state == 1) {
                    holder.mine1.visibility = View.VISIBLE
                } else if (item.state == 2) {
                    holder.mine2.visibility = View.VISIBLE
                } else if (item.state == 3) {
                    holder.mine3.visibility = View.VISIBLE
                } else if (item.state == 4) {
                    holder.mine4.visibility = View.VISIBLE
                } else if (item.state == 5) {
                    holder.mine5.visibility = View.VISIBLE
                } else if (item.state == 6) {
                    holder.mine6.visibility = View.VISIBLE
                } else if (item.state == 7) {
                    holder.mine7.visibility = View.VISIBLE
                } else if (item.state == 8) {
                    holder.mine8.visibility = View.VISIBLE
                    /**BOMBÁRA KATTINTUNK, TEHÁT VÉGE A JÁTÉKNAK*/
                } else if (item.state == 9) {
                    holder.bomb.visibility = View.VISIBLE
                    listener.gameOver()
                }else if (item.state == 0) {
                    holder.no_value_mine.visibility = View.VISIBLE
                    listener.itemClicked(position)
                }

                var db: Int = 0
                var bombs: Int = 0
                for(i in 0..99){
                    if (cellList[i].isOpened == true && cellList[i].state != 9) db+=1
                    if (cellList[i].isOpened == true && cellList[i].state == 9) bombs+=1
                }
                //System.out.println(db)
                //System.out.println(numberOfBombs)
                if (100 - db == numberOfBombs && bombs == 0)
                    listener.gameWon()


                var flagsUsed =0
                for(i in cellList){
                    if(i.isFlagged){
                        flagsUsed--
                    }
                }
            }else if (!item.isOpened && mineGrid.isFlag && !item.isFlagged){
                holder.base_cell.visibility = View.GONE
                item.isFlagged = true
                item.isOpened = false
                holder.flag.visibility = View.VISIBLE
                listener.flagClicked()
            } else if(!item.isOpened && mineGrid.isFlag && item.isFlagged){
                holder.flag.visibility = View.GONE
                item.isFlagged = false
                item.isOpened = false
                holder.base_cell.visibility = View.VISIBLE
            }
        }

        holder.flag.setOnClickListener {
            if(!item.isOpened && mineGrid.isFlag && item.isFlagged){
                holder.flag.visibility = View.GONE
                item.isFlagged = false
                item.isOpened = false
                holder.base_cell.visibility = View.VISIBLE
            }
        }


    }





}