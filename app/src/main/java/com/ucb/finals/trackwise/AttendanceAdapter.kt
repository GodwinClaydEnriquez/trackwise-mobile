package com.ucb.finals.trackwise

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AttendanceAdapter(private val attendanceList: List<Attendance>) :
    RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    inner class AttendanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.attendanceId)
        val nameTextView: TextView = itemView.findViewById(R.id.attendanceName)
        val edpCodeTextView: TextView = itemView.findViewById(R.id.attendanceEdpCode)
        val subjectTextView: TextView = itemView.findViewById(R.id.attendanceSubject)
        val departmentOrSectionTextView: TextView = itemView.findViewById(R.id.attendanceDepartmentOrSection)
        val teachingOrInstructorTextView: TextView = itemView.findViewById(R.id.attendanceTeachingOrInstructor)
        val remarksTextView: TextView = itemView.findViewById(R.id.attendanceRemarks)
        val timeInTextView: TextView = itemView.findViewById(R.id.attendanceTimeIn)
        val timeOutTextView: TextView = itemView.findViewById(R.id.attendanceTimeOut)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attendance, parent, false)
        return AttendanceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val currentItem = attendanceList[position]

        holder.idTextView.text = currentItem.id
        holder.nameTextView.text = currentItem.name
        holder.edpCodeTextView.text = currentItem.edpCode
        holder.subjectTextView.text = currentItem.subject
        holder.departmentOrSectionTextView.text = currentItem.departmentOrSection
        holder.teachingOrInstructorTextView.text = currentItem.teachingOrInstructor
        holder.remarksTextView.text = currentItem.remarks
        holder.timeInTextView.text = currentItem.timeIn
        holder.timeOutTextView.text = currentItem.timeOut
    }

    override fun getItemCount() = attendanceList.size
}
