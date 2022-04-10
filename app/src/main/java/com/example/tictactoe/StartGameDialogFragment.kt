package com.example.tictactoe


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tictactoe.databinding.GameStartLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class StartGameDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: GameStartLayoutBinding
    internal lateinit var listener: GameDialogListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameStartLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playGame.setOnClickListener {
            val numberOfRounds = binding.numerOfRounds.text.toString().toInt()
            val player = when (binding.devicePlayerButton.checkedRadioButtonId) {
                R.id.play_with_device -> 1
                else -> 2
            }
            listener.playGame(numberOfRounds, player)
            dismiss()
        }
    }

    interface GameDialogListener {
        fun playGame(rounds: Int, playerType: Int)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as GameDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                (context.toString() +
                        "must implement NoticeDialogListener")
            )
        }
    }

}





