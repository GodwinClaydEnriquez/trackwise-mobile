package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ucb.finals.trackwise.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val subjects = listOf("Intprog", "Imdbsys", "Sysarch", "Sysadmin")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.subjectRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.subjectRecyclerView.adapter = SubjectAdapter(subjects, requireFragmentManager())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
