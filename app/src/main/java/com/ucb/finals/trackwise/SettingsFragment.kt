package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ucb.finals.trackwise.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val settingsItems = listOf(
            SettingsItem(R.drawable.settings_notifications, "Notification"),
            SettingsItem(R.drawable.settings_darkmode, "Dark Mode"),
            SettingsItem(R.drawable.settings_rate, "Rate App"),
            SettingsItem(R.drawable.settings_share, "Share App"),
            SettingsItem(R.drawable.settings_privacypolicy, "Privacy Policy"),
            SettingsItem(R.drawable.settings_termsandconditions, "Terms and Conditions"),
            SettingsItem(R.drawable.settings_cookiespolicy, "Cookies Policy"),
            SettingsItem(R.drawable.settings_contact, "Contact"),
            SettingsItem(R.drawable.settings_feedback, "Feedback")
        )

        val adapter = SettingsAdapter(settingsItems, requireContext())
        binding.recyclerViewSettings.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSettings.adapter = adapter

        return binding.root
    }
}