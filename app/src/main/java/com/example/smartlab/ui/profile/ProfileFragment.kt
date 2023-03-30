package com.example.smartlab.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentHomeBinding
import com.example.smartlab.databinding.FragmentProfileBinding
import com.example.smartlab.ui.home.HomeFragmentArgs
import com.example.smartlab.ui.home.HomeViewModel
import com.example.smartlab.ui.main.MainViewModel

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val sharedPrefs: SharedPreferences by lazy { requireActivity().getSharedPreferences("PROFILE", Context.MODE_PRIVATE) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() = with(binding) {
        etName.setText(sharedPrefs.getString("NAME", ""))
        etSecondName.setText(sharedPrefs.getString("SECONDNAME", ""))
        etSurname.setText(sharedPrefs.getString("SURNAME", ""))
        etBirthday.setText(sharedPrefs.getString("BITHDATE", ""))
        spGender.setText(sharedPrefs.getString("GENDER", ""))

        binding.btnCreate.setOnClickListener {
            sharedPrefs.edit().putString("NAME", binding.etName.text.toString()).apply()
            sharedPrefs.edit().putString("SURNAME", binding.etSurname.text.toString()).apply()
            sharedPrefs.edit().putString("SECONDNAME", binding.etSecondName.text.toString()).apply()
            sharedPrefs.edit().putString("BITHDATE", binding.etBirthday.text.toString()).apply()
            sharedPrefs.edit().putString("GENDER", binding.spGender.text.toString()).apply()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}