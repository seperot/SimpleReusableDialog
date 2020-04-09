package dev.ijh.simplereusabledialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import dev.ijh.reusabledialog.ReusableDialog
import dev.ijh.reusabledialog.ReusableDialog.Companion.registerButton
import dev.ijh.reusabledialog.ReusableDialogListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        //Showing a few variants of the dialog

        btndemo.setOnClickListener {
            registerButton(
                getString(R.string.dialog_one_button_one),
                R.color.colorPrimary,
                android.R.color.white,
                object : ReusableDialogListener {
                    override fun onDialogButtonClick(dialogFragment: DialogFragment, index: Int) {
                        dialogFragment.dismiss()
                    }})

            ReusableDialog.dialogCancelable(false)

            ReusableDialog.createDialogInstance(getString(R.string.dialog_one), getString(R.string.dialog_one_text), null).show(
                supportFragmentManager, null
            )
        }

        btndemo2.setOnClickListener {
            registerButton(
                getString(R.string.dialog_two_button_one),
                R.color.colorPrimary,
                android.R.color.black,
                object: ReusableDialogListener {
                    override fun onDialogButtonClick(dialogFragment: DialogFragment, index: Int) {
                    dialogFragment.dismiss()
                    Toast.makeText(applicationContext, getString(R.string.toast_one), Toast.LENGTH_LONG).show()
                }})

            registerButton(
                getString(R.string.dialog_two_button_two),
                R.color.colorPrimaryDark,
                android.R.color.white,
               object: ReusableDialogListener {
                   override fun onDialogButtonClick(dialogFragment: DialogFragment, index: Int) {
                       dialogFragment.dismiss()
                       Toast.makeText(
                           applicationContext,
                           getString(R.string.toast_two),
                           Toast.LENGTH_LONG
                       ).show()
                   }})

            ReusableDialog.createDialogInstance(
                getString(R.string.dialog_two),
                getString(R.string.dialog_two_text),
                R.drawable.pupcat
            ).show(
                supportFragmentManager, null
            )
        }

        btndemo3.setOnClickListener {
            ReusableDialog.createDialogInstance(
                getString(R.string.demo_three_title),
                getString(R.string.demo_three_text),
                android.R.drawable.ic_dialog_alert
            ).show(
                supportFragmentManager, null
            )
        }

    }
}