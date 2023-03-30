package com.example.smartlab.ui.card

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smartlab.R
import com.example.smartlab.core.hideKeyboard
import com.example.smartlab.databinding.FragmentCardBinding
import com.example.smartlab.databinding.FragmentOnBoardBinding
import com.example.smartlab.model.Profile
import com.example.smartlab.ui.main.MainViewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class CardFragment : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        setupBirthday()
        tvSkip.setOnClickListener {
            findNavController().navigate(
                CardFragmentDirections.actionCardFragmentToHomeFragment(
                    "", "", "", "", ""
                )
            )
        }
        btnCreate.setOnClickListener {
            findNavController().navigate(
                CardFragmentDirections.actionCardFragmentToHomeFragment(
                    binding.etName.text.toString(),
                    binding.etSecondName.text.toString(),
                    binding.etSurname.text.toString(),
                    binding.etBirthday.text.toString(),
                    binding.spGender.text.toString()
                )
            )
        }
        setupFields()
    }

    private fun setupFields() = with(binding) {
        etBirthday.addTextChangedListener(setupTextWatcher())
        etName.addTextChangedListener(setupTextWatcher())
        etSurname.addTextChangedListener(setupTextWatcher())
        etSecondName.addTextChangedListener(setupTextWatcher())
        spGender.addTextChangedListener(setupTextWatcher())
    }

    private fun setupTextWatcher() = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            s?.length?.let {
                binding.btnCreate.isEnabled =
                    binding.etName.text.isNotEmpty() && binding.etSecondName.text.isNotEmpty() && binding.etSurname.text.isNotEmpty() && binding.etBirthday.text.isNotEmpty() && binding.spGender.text.isNotEmpty()
            }

        }
    }

    private fun setupBirthday() = with(binding) {
        val slots = UnderscoreDigitSlotsParser().parseSlots("__.__.____")
        val formatWatcher = MaskFormatWatcher(MaskImpl.createTerminated(slots))
        formatWatcher.installOn(etBirthday)
        etBirthday.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 10) {
                    binding.btnCreate.isEnabled = true
                    etBirthday.hideKeyboard()
                } else {
                    binding.btnCreate.isEnabled = false
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}