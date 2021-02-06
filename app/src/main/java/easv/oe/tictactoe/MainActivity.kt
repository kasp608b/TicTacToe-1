package easv.oe.tictactoe

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val TAG = "xyz"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCreate.setOnClickListener { v -> createBoard()}
    }

    private fun createBoard() {
        val rows = Integer.parseInt(etRows.text.toString())
        val cols = Integer.parseInt(etCols.text.toString())
        initializeBoard(rows, cols)
        Log.d(TAG, "createBoard")

    }


    private fun initializeBoard(rows: Int, cols: Int) {

        gameboard.removeAllViewsInLayout()
        for (x in 0 until rows) {
            val currentLL = LinearLayout(this)
            gameboard.addView(currentLL)
            currentLL.orientation = LinearLayout.HORIZONTAL
            for (y in 0 until cols) {
                val b = BoardField(this, x, y)
                b.setOnClickListener { v -> onClickBoardField(v as BoardField) }
                currentLL.addView(b)
                Log.d(TAG, "field created ($x, $y)")
            }
        }

    }

    fun onClickBoardField(b: BoardField) {
        Log.d(TAG, "Field (" + b.row + "," + b.col + ") clicked");
        b.nextState();
    }

    class BoardField : AppCompatImageView {
        var row = 0
        var col = 0

        private var state = 0

        constructor(ct: MainActivity, r: Int, c: Int) : super(ct) {
            row = r
            col = c
            state = R.drawable.circlewhite
            setImageResource(state)
        }


        fun nextState() {
           when (state) {
               R.drawable.circlewhite -> state = R.drawable.circlegreen
               R.drawable.circlegreen -> state = R.drawable.circlered
               R.drawable.circlered -> state = R.drawable.circlewhite
            }
            setImageResource(state)
        }


    }
}