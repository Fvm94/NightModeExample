package com.franciscovm.nightmodeexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            var modeNight = 0

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.main_night_on_rb ->
                    if (checked) {
                        modeNight = AppCompatDelegate.MODE_NIGHT_YES
                    }
                R.id.main_night_off_rb ->
                    if (checked) {
                        modeNight = AppCompatDelegate.MODE_NIGHT_NO
                    }
                R.id.main_night_auto_rb ->
                    if (checked){
                        modeNight = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
            }

            AppCompatDelegate.setDefaultNightMode(modeNight)

        }
    }

}