package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ucb.finals.trackwise.databinding.FragmentAnalyticsBinding

class AnalyticsFragment : Fragment() {

    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calculateAndDisplayAnalytics()
    }

    private fun calculateAndDisplayAnalytics() {
        val weeklyAttendance = getAttendanceData("weekly")
        val monthlyAttendance = getAttendanceData("monthly")
        val yearlyAttendance = getAttendanceData("yearly")

        binding.tvWeeklyAbsent.text = "Absents: ${weeklyAttendance.absentPercentage}%"
        binding.tvWeeklyPresent.text = "Presents: ${weeklyAttendance.presentPercentage}%"

        binding.tvMonthlyAbsent.text = "Absents: ${monthlyAttendance.absentPercentage}%"
        binding.tvMonthlyPresent.text = "Presents: ${monthlyAttendance.presentPercentage}%"

        binding.tvYearlyAbsent.text = "Absents: ${yearlyAttendance.absentPercentage}%"
        binding.tvYearlyPresent.text = "Presents: ${yearlyAttendance.presentPercentage}%"
    }

    private fun getAttendanceData(period: String): Attendance1 {
        val totalDays = when (period) {
            "weekly" -> 7
            "monthly" -> 30
            "yearly" -> 365
            else -> 0
        }
        val absentDays = (0..totalDays).random() // Simulate absent days
        val presentDays = totalDays - absentDays

        return Attendance1(
            absentPercentage = (absentDays * 100 / totalDays),
            presentPercentage = (presentDays * 100 / totalDays)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class Attendance1(
    val absentPercentage: Int,
    val presentPercentage: Int
)
