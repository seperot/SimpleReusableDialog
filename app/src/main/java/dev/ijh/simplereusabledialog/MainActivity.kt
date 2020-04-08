package dev.ijh.simplereusabledialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import dev.ijh.simplereusabledialog.ReusableDialog.ReusableDialogButtonData
import dev.ijh.simplereusabledialog.ReusableDialog.ReusableDialogListenerSingle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        //Showing two variants of the dialog

        btndemo.setOnClickListener {
            val confirm = ReusableDialogButtonData(
                getString(R.string.dialog_one_button_one),
                object : ReusableDialogListenerSingle {
                    override fun onDialogConfirmClick(dialogFragment: DialogFragment, index: Int) {
                        dialogFragment.dismiss()
                    }},
                ReusableDialogButtonData.FIRST_BUTTON_BG_DRAWABLE,
                ReusableDialogButtonData.FIRST_BUTTON_DEFAULT_TEXT_COLOR
            )

            val data = ArrayList<ReusableDialogButtonData>()
            data.add(confirm)
            ReusableDialog.createDialogInstance(
                getString(R.string.dialog_one),
                getString(R.string.dialog_one_text),
                data
            ).show(
                supportFragmentManager, null
            ) }

        btndemo2.setOnClickListener {   val confirm = ReusableDialog.ReusableDialogButtonData(
            getString(R.string.dialog_two_button_one),
            object : ReusableDialogListenerSingle {
                override fun onDialogConfirmClick(dialogFragment: DialogFragment, index: Int) {
                    dialogFragment.dismiss()
                    Toast.makeText(applicationContext, getString(R.string.toast_one), Toast.LENGTH_LONG).show()
                }},
            ReusableDialogButtonData.FIRST_BUTTON_BG_DRAWABLE,
            ReusableDialogButtonData.FIRST_BUTTON_DEFAULT_TEXT_COLOR
        )

            val exit = ReusableDialog.ReusableDialogButtonData(
                getString(R.string.dialog_two_button_two),
                object : ReusableDialogListenerSingle {
                    override fun onDialogConfirmClick(dialogFragment: DialogFragment, index: Int) {
                        dialogFragment.dismiss()
                        Toast.makeText(applicationContext, getString(R.string.toast_two), Toast.LENGTH_LONG).show()

                    }},
                ReusableDialogButtonData.SECOND_BUTTON_BG_DRAWABLE,
                ReusableDialogButtonData.SECOND_BUTTON_DEFAULT_TEXT_COLOR
            )
            val data = ArrayList<ReusableDialog.ReusableDialogButtonData>()
            data.add(confirm)
            data.add(exit)
            ReusableDialog.createDialogInstance(
                getString(R.string.dialog_two),
                getString(R.string.dialog_two_text),
                data
            ).show(
                supportFragmentManager, null
            ) }
    }
}