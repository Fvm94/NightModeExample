package com.franciscovm.nightmodeexample

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private var currentNightModeID = AppCompatDelegate.MODE_NIGHT_UNSPECIFIED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //GetSharedPreferences
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        currentNightModeID = sharedPref.getInt(getString(R.string.saved_night_mode_key),AppCompatDelegate.MODE_NIGHT_UNSPECIFIED)

        setNightMode(currentNightModeID)

        val button = findViewById<Button>(R.id.main_theme_bt)
        button.setOnClickListener {
            showDialog()
        }
    }

    private fun convertedCurrentNightMode(): Int {

        val nightMode = currentNightModeID
        var i = AppCompatDelegate.MODE_NIGHT_UNSPECIFIED

        when (nightMode) {
            AppCompatDelegate.MODE_NIGHT_NO ->
                i = 0
            AppCompatDelegate.MODE_NIGHT_YES ->
                i = 1
        }
        return i
    }

    private fun showDialog() {

        val builder = AlertDialog.Builder(this)
        var selectedMode = convertedCurrentNightMode()

        builder.setTitle(getString(R.string.choose_theme))
                .setSingleChoiceItems(R.array.themes,
                        selectedMode
                ) {_,  which ->
                    selectedMode = which
                }.setPositiveButton(getString(R.string.ok)) {_,_->
                    changeTheme(selectedMode)
                    Log.i(TAG, "Positive pressed")
                }.setNegativeButton(getString(R.string.cancel)) { _, _ ->
                    Log.i(TAG, "Negative pressed")
                }.create().show()
    }

    private fun changeTheme(selectedMode: Int) {

        var nightMode = AppCompatDelegate.MODE_NIGHT_UNSPECIFIED

        when (selectedMode) {
            0 ->
                nightMode = AppCompatDelegate.MODE_NIGHT_NO
            1 ->
                nightMode = AppCompatDelegate.MODE_NIGHT_YES
        }

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putInt(getString(R.string.saved_night_mode_key), nightMode)
            commit()
        }
        setNightMode(nightMode)
    }

    private fun setNightMode(id:Int){
        AppCompatDelegate.setDefaultNightMode(id)
    }

    companion object {
        private const val TAG = "MainActivity.Inf"
    }
}

