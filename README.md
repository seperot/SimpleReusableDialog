[![](https://jitpack.io/v/seperot/SimpleReusableDialog.svg)](https://jitpack.io/#seperot/SimpleReusableDialog)
# SimpleReusableDialog
A simple reusable dialog that allows custom buttons, all with native Android/Kotlin

### Latest Version
`implementation 'com.github.seperot:SimpleReusableDialog:1.4'`

### Hows it work?
If you check the [Main Activity](app/src/main/java/dev/ijh/simplereusabledialog/MainActivity.kt) you can see some examples of it working but to explain the main parts

#### registerButton
RegisterButton adds a button to the next time you call createDialogInstance it has four parts
buttonTitle: The text the button shows
buttonBackground: The button background color, things like R.color or android.R.color work
textColor: The button text color, again things like R.color or android.R.color work
dialogButtonListener: The button onClickListener, this has to be called in the following format currently until I can figure out how to make it look nicer

` object : ReusableDialogListener {
                    override fun onDialogButtonClick(dialogFragment: DialogFragment, index: Int) {
                        dialogFragment.dismiss()
                    }})`
                    
#### dialogCancelable
A simple boolean that turns on or off the users ability to cancel out of the dialog without choosing an option. This also needs to be done before you call createDialogInstance and will reset after use
true = they can cancel, false = they cannot cancel
This also works for the back button

#### createDialogInstance
Calls the dialog and shows it to the user, grabs any regisered buttons and checks if the dialog is cancelble or not then displays. Has two fields
title: The dialog title
nfoText: The dialog content
also needs a

`.show(supportFragmentManager, null)`

At the end to display

### Planned features
* Add a setting for custom button margin
* Add a setting for custom button padding 
* Add Icon option at the top
* Add set dialog size and scroll    
