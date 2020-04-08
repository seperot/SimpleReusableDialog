package dev.ijh.reusabledialog

import androidx.fragment.app.DialogFragment

interface ReusableDialogListener {
    fun onDialogButtonClick(dialogFragment: DialogFragment, index: Int)
}