/**
 * rolls a dice and shows the output
 * sides of the dice can be interchanged between 2 and 6
 */

package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
//import com.example.diceroller.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // roll the dice on app start so as to not have a rather empty/confusing start-screen
        rollDice()

        // rolls the dice
        val rollBut: Button = findViewById(R.id.button)
        rollBut.setOnClickListener { rollDice() }

        // changes the side count of the dice
        val changeSidesBut: Button = findViewById(R.id.button2)
        changeSidesBut.setOnClickListener { changeSides() }
    }

    private var sides = 6  // current no of sides
    private var baari = true  // controls side change

    private fun changeSides() {
        Toast.makeText(this, "Side count Changed!", LENGTH_SHORT).show()
        val currSidesOut: TextView = findViewById(R.id.textView2)
        sides = (if (baari) 2 else 6)
        baari = !baari
        currSidesOut.text = sides.toString()
    }

    private fun rollDice() {
//        Toast.makeText(this, "Dice Rolled, HURRAY!!🔥", LENGTH_SHORT).show()
        val dice1 = Dice(sides)
        val ans = dice1.roll()

        // locate the imageView and update it's content description
        val rollRes: ImageView = findViewById(R.id.imageView)
        rollRes.contentDescription = ans.toString()

        // select the correct drawable to update according to the dice roll result
        val diceRes = when (ans) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        rollRes.setImageResource(diceRes)  // update the imageView
    }
}

class Dice(private val sides: Int) {
    fun roll(): Int {
        return (1..sides).random()
    }
}