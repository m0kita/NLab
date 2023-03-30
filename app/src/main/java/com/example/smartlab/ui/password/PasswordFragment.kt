package com.example.smartlab.ui.password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.davidmiguel.numberkeyboard.NumberKeyboardListener
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentOnBoardBinding
import com.example.smartlab.databinding.FragmentPasswordBinding

class PasswordFragment : Fragment() {
    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        setupKeyboard()
        binding.tvSkip.setOnClickListener {
            findNavController().navigate(R.id.action_passwordFragment_to_cardFragment)
        }
    }

    private fun setupKeyboard() = with(binding) {
        pinPassword.setOnPinChangeListener(
            onPinChange = { pin ->

            },
            onPinComplete = {
                findNavController().navigate(R.id.action_passwordFragment_to_cardFragment)
            }
        )
        keyboard.setListener(object : NumberKeyboardListener {
            override fun onLeftAuxButtonClicked() {}
            override fun onNumberClicked(number: Int) {
                pinPassword.addPinCode(number.toString())
            }

            override fun onRightAuxButtonClicked() {
                pinPassword.removePinCode()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}