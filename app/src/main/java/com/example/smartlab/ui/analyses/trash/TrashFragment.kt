package com.example.smartlab.ui.analyses.trash

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentAnalysesBinding
import com.example.smartlab.databinding.FragmentTrashBinding
import com.example.smartlab.ui.analyses.adapter.TrashAdapter

class TrashFragment : Fragment() {
    private var _binding: FragmentTrashBinding? = null
    private val binding get() = _binding!!
    private val navArgs by navArgs<TrashFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val names = navArgs.names.split("%$%")
        val prices = navArgs.prices.split("%$%")
        binding.tvCount.text = getString(R.string.count, navArgs.allPrice.toString())
        binding.rvTrash.adapter = TrashAdapter(names.toMutableList(), prices.toMutableList(), requireContext()) {findNavController().navigate(TrashFragmentDirections.actionTrashFragmentToAnalysesFragment(true))}
        binding.ivTrash.setOnClickListener {
            binding.rvTrash.visibility = View.GONE
            findNavController().popBackStack()
        }
        binding.ibtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}