package com.example.smartlab.ui.code

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentCodeBinding
import com.example.smartlab.databinding.FragmentOnBoardBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CodeFragment : Fragment() {
    private val viewModel: CodeViewModel by viewModels()
    private val navArgs by navArgs<CodeFragmentArgs>()
    private var _binding: FragmentCodeBinding? = null
    private val binding get() = _binding!!
    private val countDownTimer = object : CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val seconds = (millisUntilFinished / 1000).toInt()
            binding.tvRepeatSendCode.text = getString(R.string.timer, seconds.toString())
        }

        override fun onFinish() {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        startTimer()
        binding.etCode.setOnCodeChangedListener { (code, completed) ->
            if (completed) {
                lifecycleScope.launch {
                    viewModel.checkCode(navArgs.email, code)
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.token.collect {
                Log.d("Aboba", it)
                if (it != "Оибка в логине или пароле." && it != "") {
                    countDownTimer.cancel()
                    findNavController().navigate(R.id.action_codeFragment_to_passwordFragment)
                } else {
                    startTimer()
                }
            }
        }
        binding.ibtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_codeFragment_to_emailFragment)
            countDownTimer.cancel()
        }
    }

    private fun startTimer() {
        countDownTimer.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
        _binding = null
    }
}