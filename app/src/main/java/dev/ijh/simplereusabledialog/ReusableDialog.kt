package dev.ijh.simplereusabledialog

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_reusable_dialog.*
import java.util.*

class ReusableDialog : DialogFragment() {

    private var title: String? = null
    private var infoText: String? = null
    private var dialogButtonsData: ArrayList<ReusableDialogButtonData>? = null

    interface ReusableDialogListenerSingle {
        fun onDialogConfirmClick(dialogFragment: DialogFragment, index: Int)
    }

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
                btnOption.setOnClickListener { buttonData.reusableDialogListener.onDialogConfirmClick(dialog, position) }
                llButtonArea?.addView(btnOption)
            }
        }
    }

    /**
     * General construct for ReusableDialogButtonData
     *
     * @param buttonTitle            - The Button Title
     * @param reusableDialogListener - The button listener
     * @param buttonBg               - The button color [.FIRST_BUTTON_BG_DRAWABLE], [.SECOND_BUTTON_BG_DRAWABLE], ETC
     * @param textColor              - The button text color [.FIRST_BUTTON_DEFAULT_TEXT_COLOR], [.SECOND_BUTTON_DEFAULT_TEXT_COLOR], ETC
     */
    class ReusableDialogButtonData(var buttonTitle: String,
                                   var reusableDialogListener: ReusableDialogListenerSingle,
                                   var buttonBg: Int, var textColor: Int) {
        companion object {
            const val FIRST_BUTTON_DEFAULT_TEXT_COLOR = android.R.color.holo_blue_bright
            const val SECOND_BUTTON_DEFAULT_TEXT_COLOR = android.R.color.white
            const val FIRST_BUTTON_BG_DRAWABLE = R.drawable.button_primary
            const val SECOND_BUTTON_BG_DRAWABLE = R.drawable.button_secondary
        }
    }

    companion object {
        /**
         * Create ReusableDialog instance
         *
         * @param title    - The dialog title
         * @param infoText - The dialog Content message
         * @param data     - the dialog buttons Array [ReusableDialogButtonData]
         * @return the ReusableDialog instance
         */
        fun createDialogInstance(title: String?, infoText: String?, data: ArrayList<ReusableDialogButtonData>?): ReusableDialog {
            val dialog = ReusableDialog()
            val args = Bundle()
            dialog.title = title
            dialog.infoText = infoText
            dialog.arguments = args
            dialog.dialogButtonsData = data
            return dialog
        }
    }
}