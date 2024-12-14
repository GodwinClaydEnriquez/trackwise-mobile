package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucb.finals.trackwise.databinding.FragmentSubjectBinding

class SubjectFragment : Fragment() {

    private var _binding: FragmentSubjectBinding? = null
    private val binding get() = _binding!!

    private val edpCodes = generateEDPCodes()

    companion object {
        private const val ARG_SUBJECT = "subject"

        fun newInstance(subject: String): SubjectFragment {
            val fragment = SubjectFragment()
            val args = Bundle()
            args.putString(ARG_SUBJECT, subject)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.edpRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.edpRecyclerView.adapter = EDPAdapter(edpCodes, requireFragmentManager(), arguments?.getString(ARG_SUBJECT) ?: "")
    }

    private fun generateEDPCodes(): List<Pair<String, String>> {
        val codes = mutableListOf<Pair<String, String>>()
        val startTime = 7 * 60 + 30 // 7:30 AM in minutes
        val endTime = 21 * 60 + 30 // 9:30 PM in minutes
        val interval = 60 // 1 hour

        for (time in startTime until endTime step interval) {
            val hours = time / 60
            val minutes = time % 60
            val timeString = String.format("%02d:%02d", hours, minutes)
            val nextTime = time + interval
            val nextHours = nextTime / 60
            val nextMinutes = nextTime % 60
            val nextTimeString = String.format("%02d:%02d", nextHours, nextMinutes)
            val edpCode = "EDP${timeString.replace(":", "")}"
            codes.add(Pair(edpCode, "$timeString - $nextTimeString"))
        }

        return codes
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
