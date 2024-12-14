package com.ucb.finals.trackwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class EDPAdapter(private val edpCodes: List<Pair<String, String>>, private val fragmentManager: FragmentManager, private val subject: String) :
    RecyclerView.Adapter<EDPAdapter.EDPViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EDPViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_edp_code, parent, false)
        return EDPViewHolder(view)
    }

    override fun onBindViewHolder(holder: EDPViewHolder, position: Int) {
        holder.bind(edpCodes[position])
    }

    override fun getItemCount() = edpCodes.size

    inner class EDPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val edpCode: TextView = itemView.findViewById(R.id.edpCode)
        private val timeSchedule: TextView = itemView.findViewById(R.id.timeSchedule)

        fun bind(code: Pair<String, String>) {
            edpCode.text = code.first
            timeSchedule.text = code.second
            itemView.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AttendanceFragment.newInstance(code.first, subject))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
