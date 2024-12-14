package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ucb.finals.trackwise.databinding.FragmentAttendanceBinding

class AttendanceFragment : Fragment() {

    private var _binding: FragmentAttendanceBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val ARG_EDP_CODE = "edp_code"
        private const val ARG_SUBJECT = "subject"

        fun newInstance(edpCode: String, subject: String): AttendanceFragment {
            val fragment = AttendanceFragment()
            val args = Bundle()
            args.putString(ARG_EDP_CODE, edpCode)
            args.putString(ARG_SUBJECT, subject)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        val edpCode = arguments?.getString(ARG_EDP_CODE)
        val subject = arguments?.getString(ARG_SUBJECT)

        binding.instructorRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.instructorRecyclerView.adapter = AttendanceAdapter(generateInstructorAttendanceData(edpCode, subject))
        binding.instructorRecyclerView.addItemDecoration(TableItemDecoration())

        binding.studentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.studentRecyclerView.adapter = AttendanceAdapter(generateStudentAttendanceData(edpCode, subject))
        binding.studentRecyclerView.addItemDecoration(TableItemDecoration())
    }

    private fun generateInstructorAttendanceData(edpCode: String?, subject: String?): List<Attendance> {
        return listOf(
            Attendance("18535369", "Joey Velasco", edpCode, subject, "PE", "Teaching", "Present", "07:25 AM", "08:30 AM")
        )
    }

    private fun generateStudentAttendanceData(edpCode: String?, subject: String?): List<Attendance> {
        return listOf(
            Attendance("1853567", "John Doe", edpCode, subject, "A", "Joey Velasco", "Late", "07:45 AM", "08:30 AM")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

