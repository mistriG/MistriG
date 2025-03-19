package com.example.mistrig.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import com.example.mistrig.R

class NotificationPreferenceFragment : Fragment() {

    private lateinit var enableNotificationsSwitch: Switch
    private lateinit var promoNotificationsCheckbox: CheckBox
    private lateinit var serviceNotificationsCheckbox: CheckBox
    private lateinit var reminderNotificationsCheckbox: CheckBox
    private lateinit var notificationSoundSpinner: Spinner
    private lateinit var vibrationSwitch: Switch
    private lateinit var notificationFrequencySeekbar: SeekBar
    private lateinit var notificationFrequencyValue: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_notification_preference, container, false)

        enableNotificationsSwitch = view.findViewById(R.id.enable_notifications_switch)
        promoNotificationsCheckbox = view.findViewById(R.id.promo_notifications_checkbox)
        serviceNotificationsCheckbox = view.findViewById(R.id.service_notifications_checkbox)
        reminderNotificationsCheckbox = view.findViewById(R.id.reminder_notifications_checkbox)
        notificationSoundSpinner = view.findViewById(R.id.notification_sound_spinner)
        vibrationSwitch = view.findViewById(R.id.vibration_switch)
        notificationFrequencySeekbar = view.findViewById(R.id.notification_frequency_seekbar)
        notificationFrequencyValue = view.findViewById(R.id.notification_frequency_value)

        // Set SeekBar change listener
        notificationFrequencySeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                notificationFrequencyValue.text = "Frequency: ${getFrequencyLabel(progress)}"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }
        })


        return view
    }

    private fun getFrequencyLabel(progress: Int): String {
        return when {
            progress < 33 -> "Low"
            progress < 67 -> "Medium"
            else -> "High"
        }
    }

}