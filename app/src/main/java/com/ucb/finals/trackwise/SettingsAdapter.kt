package com.ucb.finals.trackwise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class SettingsAdapter(private val items: List<SettingsItem>, private val context: Context) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_setting, parent, false)
        return SettingsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        val item = items[position]
        holder.icon.setImageResource(item.iconResId)
        holder.title.text = item.title

        when (item.title) {
            "Notification" -> {
                holder.switch.visibility = View.VISIBLE
                holder.switch.setOnCheckedChangeListener  { _, isChecked ->
                    if (isChecked) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Notification ON",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            "Notification OFF",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            "Dark Mode" -> {
                holder.switch.visibility = View.VISIBLE
                holder.switch.setOnCheckedChangeListener  { _, isChecked ->
                    if (isChecked) {
                        Toast.makeText(
                            holder.itemView.context,
                            "Dark Mode ON",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            holder.itemView.context,
                            "Dark Mode OFF",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            "Rate App" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, RateAppFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            "Share App" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, ShareAppFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            "Privacy Policy" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, PrivacyPolicyFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            "Terms and Conditions" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, TermsAndConditionsFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            "Cookies Policy" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, CookiesPolicyFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            "Contact" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, ContactFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            "Feedback" -> {
                holder.itemView.setOnClickListener {
                    (holder.itemView.context as? FragmentActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, FeedbackFragment())
                        ?.addToBackStack(null)
                        ?.commit()
                }
            }
            // Add more cases as needed...
            else -> {
                holder.switch.visibility = View.GONE
            }
        }
    }

    override fun getItemCount() = items.size

    class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.settingIcon)
        val title: TextView = view.findViewById(R.id.settingTitle)
        val switch: Switch = view.findViewById(R.id.notificationSwitch)
    }
}