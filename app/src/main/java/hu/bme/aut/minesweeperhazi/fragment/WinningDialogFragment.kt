package hu.bme.aut.minesweeperhazi.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.bme.aut.minesweeperhazi.databinding.DialogPlayerBinding

class WinningDialogFragment : DialogFragment() {


    interface WinnerNameDialogListener {
        fun onPlayerItemCreated(newItem: String)
    }

    /**  **/
    private lateinit var listener: WinnerNameDialogListener
    /**  **/
    private lateinit var binding: DialogPlayerBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? WinnerNameDialogListener
            ?: throw RuntimeException("Activity must implement the WinnerNameDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogPlayerBinding.inflate(LayoutInflater.from(context))



        return AlertDialog.Builder(requireContext())
            .setTitle("Enter Your Name:")
            .setView(binding.root)
            .setPositiveButton("Ok") { dialogInterface, i ->
                if (isValid()) {
                    listener.onPlayerItemCreated(getPlayerItem())
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }


    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getPlayerItem() = binding.etName.text.toString()


    companion object {
        const val TAG = "WinnerNameDialogListener"
    }
}