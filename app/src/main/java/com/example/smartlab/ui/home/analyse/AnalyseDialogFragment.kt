package com.example.smartlab.ui.home.analyse

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.domain.model.Analyse
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentAnalyseDialogBinding

class AnalyseDialogFragment(private val analyse: Analyse, private val add: (String) -> Unit): DialogFragment() {

    private var _binding: FragmentAnalyseDialogBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.bg_analyse_dialog))
        _binding = FragmentAnalyseDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    private fun setupUI() = with(binding) {
        tvAnalyseName.text = analyse.name
        tvDescription.text = analyse.description
        tvTrain.text = analyse.preparation
        tvResult.text = analyse.time_result
        tvBiomaterial.text = analyse.bio
        btnAdd.text = getString(R.string.add_by, analyse.price)
        btnClose.setOnClickListener {
            dismiss()
        }
        btnAdd.setOnClickListener {
            add(analyse.price)
            dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}