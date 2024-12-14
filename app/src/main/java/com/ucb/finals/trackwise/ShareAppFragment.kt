package com.ucb.finals.trackwise

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ucb.finals.trackwise.databinding.FragmentShareAppBinding

class ShareAppFragment : Fragment() {

    private var _binding: FragmentShareAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.shareAppButton.setOnClickListener {
            shareApp()
        }
    }

    private fun shareApp() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this amazing app: [App Link]")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share App via"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
