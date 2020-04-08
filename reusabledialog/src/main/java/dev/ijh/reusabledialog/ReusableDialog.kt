package dev.ijh.reusabledialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_reusable_dialog.*
import java.util.*

class ReusableDialog : DialogFragment(){

    private var title: String? = null
    private var infoText: String? = null
    private var dialogButtonsData: ArrayList<ReusableDialogButtonData>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reusable_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setText()
        setupButtons()
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog?.setOnCancelListener {
            dialogButtons.clear()
        }
    }

    private fun setText() {
        tvDialogTitle?.text = title
        tvDialogMain?.text = infoText
    }

    private fun buttonParams(): LinearLayout.LayoutParams {
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            resources.getDimension(R.dimen.padding_button).toInt()
        )
        params.setMargins(
            resources.getDimension(R.dimen.padding_standard).toInt(),
            resources.getDimension(R.dimen.padding_none).toInt(),
            resources.getDimension(R.dimen.padding_standard).toInt(),
            resources.getDimension(R.dimen.padding_standard).toInt())
        params.gravity = Gravity.CENTER
        return params
    }

    private fun setupButtons() {
        val dialog: DialogFragment = this
        dialogButtonsData?.let { buttonArray ->
            buttonArray.forEachIndexed { position, buttonData ->
                val btnOption = Button(context)
                btnOption.layoutParams = buttonParams()
                btnOption.setPadding(
                    resources.getDimension(R.dimen.padding_standard).toInt(),
                    resources.getDimension(R.dimen.padding_none).toInt(),
                    resources.getDimension(R.dimen.padding_standard).toInt(),
                    resources.getDimension(R.dimen.padding_none).toInt()
                )

                btnOption.text = buttonData.buttonTitle
                btnOption.gravity = Gravity.CENTER
                context?.resources?.let { ResourcesCompat.getColor(it, buttonData.textColor, null) }?.let { btnOption.setTextColor(it) }
                btnOption.background = resources.getDrawable(buttonData.buttonBg, null)
                btnOption.isAllCaps = false
                btnOption.setOnClickListener {
                    buttonData.reusableDialogListener.onDialogButtonClick(dialog, position)
                    dialogButtons.clear()
                    edgeClickDisabled = true
                }
                llButtonArea?.addView(btnOption)
            }
        }
    }

    class ReusableDialogButtonData(var buttonTitle: String, var buttonBg: Int, var textColor: Int, var reusableDialogListener: ReusableDialogListener)

    companion object {

        private var dialogButtons = ArrayList<ReusableDialogButtonData>()
        private var edgeClickDisabled = true

        //TODO: Add fun for setting custom margin, custom padding and an icon at the top

        /**
         * Switch to allow the user to exit dialog by hitting the back button or click off the dialog area
         * @param isCancelable            - True = Allowed to Cancel, False = Unable to Cancel
         */
        fun dialogCancelable(isCancelable: Boolean) {
            edgeClickDisabled = isCancelable
        }

        /**
         * General construct for RegisterButton
         * @param buttonTitle            - The Button Title
         * @param dialogButtonListener - The button onClickListener
         * @param buttonBackground               - The button background color: R.color or android.R.color work
         * @param textColor              - The button text color: R.color or android.R.color work
         */
        fun registerButton(buttonTitle: String, buttonBackground: Int, textColor: Int, dialogButtonListener: ReusableDialogListener) {
            dialogButtons.add(ReusableDialogButtonData(buttonTitle, buttonBackground, textColor, dialogButtonListener))
        }

        /**
         * Create ReusableDialog instance
         * @param title    - The dialog title
         * @param infoText - The dialog Content message
         * @param data     - the dialog buttons Array [ReusableDialogButtonData]
         * @return the ReusableDialog instance
         */
        fun createDialogInstance(title: String?,
                                 infoText: String?): ReusableDialog {
            val dialog = ReusableDialog()
            val args = Bundle()
            dialog.title = title
            dialog.infoText = infoText
            dialog.arguments = args
            dialog.isCancelable = edgeClickDisabled
            dialog.dialogButtonsData = dialogButtons
            return dialog
        }
    }
}