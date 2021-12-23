package hu.bme.aut.minesweeperhazi.data

class MineGrid(var cells:ArrayList<Cell>) {
    var isFlag = false
    var timer:Long = 0
    var seconds: Double = 0.0

    fun getScore(n:Int):Int{
        seconds = (timer / 1000).toDouble()
        return ((100.0/seconds)*n).toInt()
    }
}