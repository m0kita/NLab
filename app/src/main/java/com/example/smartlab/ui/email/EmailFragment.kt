package com.example.smartlab.ui.email

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentEmailBinding
import com.example.smartlab.databinding.FragmentOnBoardBinding
import kotlinx.coroutines.launch

class EmailFragment : Fragment() {
    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EmailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        setupEditText()
        binding.btnContinue.setOnClickListener {
            lifecycleScope.launch {
                viewModel.sendCode(binding.etEmail.text.toString())
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.code.collect{
                if(it == "Успешно код отправлен") {
                    findNavController().navigate(EmailFragmentDirections.actionEmailFragmentToCodeFragment(binding.etEmail.text.toString()))
                }
            }
        }
    }

    private fun setupEditText() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    val regex = """(.+)@(.+)\.(.+)""".toRegex()
                    val email = regex.matchEntire(s)
                    if (email != null) {
                        val (name, mail, domain) = email.destructured
                        binding.btnContinue.isEnabled = domain.length > 1
                    }
                }
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}