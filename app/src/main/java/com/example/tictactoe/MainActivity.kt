package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.tictactoe.databinding.ActivityMainBinding
import kotlin.random.Random

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), StartGameDialogFragment.GameDialogListener {

    private lateinit var binding: ActivityMainBinding
    private val startGameDialogFragment = StartGameDialogFragment()
    private lateinit var listOfButtons: List<Button>
    private lateinit var listOfRadios: List<Button>
    private val emptyCells = mutableSetOf<Int>()
    private val player1Arraylist = arrayListOf<Int>()
    private val player2Arraylist = arrayListOf<Int>()
    private var cellId: Int = 0
    private var activePlayer = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        disableButtonsOnLaunch()

        listOfButtons = listOf<Button>(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
        )
        listOfRadios = listOf<RadioButton>(
            binding.player1Radio1,
            binding.player1Radio2,
            binding.player1Radio3,
            binding.player2Radio1,
            binding.player2Radio2,
            binding.player2radio3
        )
        binding.startGame.setOnClickListener {
            startOrRestartGame()
        }
    }

    //start game startGameDialogFragment
    fun startOrRestartGame() {
        startGameDialogFragment.show(supportFragmentManager, "game")
    }



    private fun playWithPlayer(cellId: Int, buttonSelected: Button) {
        if (activePlayer == 1) {
            buttonSelected.text = "X"
            buttonSelected.setBackgroundColor(resources.getColor(R.color.blue))
            player1Arraylist.add(cellId)
            Toast.makeText(this, "Player 2 your turn", Toast.LENGTH_SHORT).show()
            activePlayer = 2

        } else {
            buttonSelected.text = "O"
            buttonSelected.setBackgroundColor(resources.getColor(R.color.yellow))
            player2Arraylist.add(cellId)
            Toast.makeText(this, "Player 1 your turn", Toast.LENGTH_SHORT).show()
            activePlayer = 1
        }

        buttonSelected.isEnabled = false
        winnerWithOthers()
    }

    private fun playWithDevice(cellId: Int, buttonSelected: Button) {
        if (activePlayer == 1) {
            buttonSelected.text = "X"
            buttonSelected.setBackgroundColor(resources.getColor(R.color.blue))
            buttonSelected.isEnabled = false
            player1Arraylist.add(cellId)
            Toast.makeText(this, "Device's turn", Toast.LENGTH_SHORT).show()
            activePlayer = 2
            devicePlay()

        } else {
            buttonSelected.text = "O"
            buttonSelected.setBackgroundColor(resources.getColor(R.color.yellow))
            player2Arraylist.add(cellId)
            Toast.makeText(this, "Your turn", Toast.LENGTH_SHORT).show()
            activePlayer = 1
            buttonSelected.isEnabled = false
        }

        winnerWithDevice()
    }

    //code to play with Device
    private fun devicePlay() {
        for (cellId in 1..9) {
            if (!(player1Arraylist.contains(cellId) || player2Arraylist.contains(cellId))) {
                emptyCells.add(cellId)
            }
        }
        val randomIndex = Random.nextInt(emptyCells.size)
        val id = emptyCells.toIntArray()[randomIndex]
        val buttonSelected: Button = when (id) {
            1 -> binding.button1
            2 -> binding.button2
            3 -> binding.button3
            4 -> binding.button4
            5 -> binding.button5
            6 -> binding.button6
            7 -> binding.button7
            8 -> binding.button8
            9 -> binding.button9
            else -> binding.button1
        }
        playWithDevice(id, buttonSelected)
    }

    //Decide winner when playing with another player
    private fun winnerWithOthers() {
        //row1
        if ((player1Arraylist.contains(1) && player1Arraylist.contains(2) && player1Arraylist.contains(
                3
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(1) && player2Arraylist.contains(2) && player2Arraylist.contains(
                3
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //row2
        if ((player1Arraylist.contains(4) && player1Arraylist.contains(5) && player1Arraylist.contains(
                6
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(4) && player2Arraylist.contains(5) && player2Arraylist.contains(
                6
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //row3
        if ((player1Arraylist.contains(7) && player1Arraylist.contains(8) && player1Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(7) && player2Arraylist.contains(8) && player2Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //col1
        if ((player1Arraylist.contains(1) && player1Arraylist.contains(4) && player1Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(1) && player2Arraylist.contains(4) && player2Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //col2
        if ((player1Arraylist.contains(2) && player1Arraylist.contains(5) && player1Arraylist.contains(
                8
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(2) && player2Arraylist.contains(5) && player2Arraylist.contains(
                8
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //col3
        if ((player1Arraylist.contains(3) && player1Arraylist.contains(6) && player1Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(3) && player2Arraylist.contains(6) && player2Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //diagonal1
        if ((player1Arraylist.contains(1) && player1Arraylist.contains(5) && player1Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(1) && player2Arraylist.contains(5) && player2Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

        //diagonal2
        if ((player1Arraylist.contains(3) && player1Arraylist.contains(5) && player1Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            endGame()
        }

        if ((player2Arraylist.contains(3) && player2Arraylist.contains(5) && player2Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            endGame()
        }

    }

    //Decide winner when playing Device
    private fun winnerWithDevice() {
        //row1
        if ((player1Arraylist.contains(1) && player1Arraylist.contains(2) && player1Arraylist.contains(
                3
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(1) && player2Arraylist.contains(2) && player2Arraylist.contains(
                3
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player2Radio1.isChecked = true
            endGame()
        }

        //row2
        if ((player1Arraylist.contains(4) && player1Arraylist.contains(5) && player1Arraylist.contains(
                6
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(4) && player2Arraylist.contains(5) && player2Arraylist.contains(
                6
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player2Radio1.isChecked = true
            endGame()
        }

        //row3
        if ((player1Arraylist.contains(7) && player1Arraylist.contains(8) && player1Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(7) && player2Arraylist.contains(8) && player2Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player2Radio1.isChecked = true
            endGame()
        }

        //col1
        if ((player1Arraylist.contains(1) && player1Arraylist.contains(4) && player1Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(1) && player2Arraylist.contains(4) && player2Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        //col2
        if ((player1Arraylist.contains(2) && player1Arraylist.contains(5) && player1Arraylist.contains(
                8
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(2) && player2Arraylist.contains(5) && player2Arraylist.contains(
                8
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        //col3
        if ((player1Arraylist.contains(3) && player1Arraylist.contains(6) && player1Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(3) && player2Arraylist.contains(6) && player2Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        //diagonal1
        if ((player1Arraylist.contains(1) && player1Arraylist.contains(5) && player1Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(1) && player2Arraylist.contains(5) && player2Arraylist.contains(
                9
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        //diagonal2
        if ((player1Arraylist.contains(3) && player1Arraylist.contains(5) && player1Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_1)
            binding.player1Radio1.isChecked = true
            endGame()
        }

        if ((player2Arraylist.contains(3) && player2Arraylist.contains(5) && player2Arraylist.contains(
                7
            ))
        ) {
            binding.imageView.setImageResource(R.drawable.player_2)
            binding.player1Radio1.isChecked = true
            endGame()
        }

    }


    //ends game
    private fun endGame() {
        listOfButtons.forEach {
            it.isEnabled = false
            it.text = null
        }
        binding.startGame.isEnabled = true
        binding.startGame.text = "RESTART GAME"


    }

    //disables all buttons at launch
    fun disableButtonsOnLaunch() {
        val listOfButtons = listOf<Button>(

            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.player1Radio1,
            binding.player1Radio2,
            binding.player1Radio3,
            binding.player2Radio1,
            binding.player2Radio2,
            binding.player2radio3
        )
        listOfButtons.forEach {
            it.isEnabled = false
        }
        binding.apply {
            player1.isVisible = false
            player2.isVisible = false
            linearLayout.isVisible = false
            linearLayout2.isVisible = false
        }
    }

    //make the game UI active
    fun enableGame() {
        listOfButtons.forEach {
            it.isEnabled = true
        }
        listOfRadios.forEach {
            it.isEnabled = true
        }
        binding.apply {
            player1.isVisible = true
            player2.isVisible = true
            linearLayout.isVisible = true
            linearLayout2.isVisible = true
        }
    }

    //restart game all over
    fun restartGame() {
        listOfButtons.forEach {
            it.setBackgroundColor(resources.getColor(R.color.white))
        }
        activePlayer = 1
        player1Arraylist.clear()
        player2Arraylist.clear()
        emptyCells.clear()
        binding.imageView.setImageDrawable(null)
    }

    override fun playGame(rounds: Int, playerType: Int) {
        if (rounds == 1 && playerType == 1) {
            enableGame()
            restartGame()
            binding.startGame.isEnabled = false
            binding.player1.text = "You"
            binding.player2.text = "Device"
            binding.player1Radio2.isClickable = false
            binding.player1Radio3.isClickable = false
            binding.player2Radio2.isClickable = false
            binding.player2radio3.isClickable = false
            binding.player1Radio1.isChecked = false
            binding.player2Radio1.isChecked = false

            for (button in listOfButtons) {
                button.setOnClickListener {
                    cellId = when (button.id) {
                        R.id.button1 -> 1
                        R.id.button2 -> 2
                        R.id.button3 -> 3
                        R.id.button4 -> 4
                        R.id.button5 -> 5
                        R.id.button6 -> 6
                        R.id.button7 -> 7
                        R.id.button8 -> 8
                        else -> 9
                    }
                    playWithDevice(cellId, button)
                }
            }
//            for (button in listOfButtons){
//                if (!button.isEnabled && !(player1Arraylist.contains(1) && player1Arraylist.contains(2) && player1Arraylist.contains(4))){
//                    binding.imageView.setImageResource(R.drawable.stalemate)
//                }
//            }
        }
    }


}