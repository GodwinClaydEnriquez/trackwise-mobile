package com.ucb.finals.trackwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class SubjectAdapter(private val subjects: List<String>, private val fragmentManager: FragmentManager) :
    RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(subjects[position])
    }

    override fun getItemCount() = subjects.size

    inner class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subjectName: TextView = itemView.findViewById(R.id.subjectName)

        fun bind(subject: String) {
            subjectName.text = subject
            itemView.setOnClickListener {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SubjectFragment.newInstance(subject))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
