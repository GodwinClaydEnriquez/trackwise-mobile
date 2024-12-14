package com.ucb.finals.trackwise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.ucb.finals.trackwise.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var listView: ListView
    private val activities = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView = binding.listViewActivities
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, activities)
        listView.adapter = adapter

        arguments?.getStringArrayList("activities")?.let {
            activities.addAll(it)
            adapter.notifyDataSetChanged()
        }
    }

    companion object {
        fun newInstance(activityList: ArrayList<String>): HistoryFragment {
            val fragment = HistoryFragment()
            val args = Bundle()
            args.putStringArrayList("activities", activityList)
            fragment.arguments = args
            return fragment
        }
    }
}