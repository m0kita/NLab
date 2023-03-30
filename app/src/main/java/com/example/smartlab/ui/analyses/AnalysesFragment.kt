package com.example.smartlab.ui.analyses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.domain.model.Analyse
import com.example.domain.model.News
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentAnalysesBinding
import com.example.smartlab.ui.analyses.adapter.AnalyseAdapter
import com.example.smartlab.ui.home.HomeFragment
import com.example.smartlab.ui.home.adapter.NewsAdapter
import com.example.smartlab.ui.home.analyse.AnalyseDialogFragment
import kotlinx.coroutines.flow.collect

class AnalysesFragment : Fragment() {
    private var _binding: FragmentAnalysesBinding? = null
    private val binding get() = _binding!!
    private val navArgs by navArgs<AnalysesFragmentArgs>()
    private val viewModel: AnalysesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
         binding.btnPopular.setOnClickListener {
             binding.btnPopular.setBackgroundColor(requireContext().getColor(R.color.blue))
             binding.btnPopular.setTextColor(requireContext().getColor(R.color.white))

             binding.btnCovid.setBackgroundColor(requireContext().getColor(R.color.lighest_gray))
             binding.btnCovid.setTextColor(requireContext().getColor(R.color.purple))
             binding.btnOnkoGenetik.setBackgroundColor(requireContext().getColor(R.color.lighest_gray))
             binding.btnOnkoGenetik.setTextColor(requireContext().getColor(R.color.purple))

             setupAnalyse(viewModel.popular)
         }
        binding.btnCovid.setOnClickListener {
            binding.btnCovid.setBackgroundColor(requireContext().getColor(R.color.blue))
            binding.btnCovid.setTextColor(requireContext().getColor(R.color.white))

            binding.btnPopular.setBackgroundColor(requireContext().getColor(R.color.lighest_gray))
            binding.btnPopular.setTextColor(requireContext().getColor(R.color.purple))
            binding.btnOnkoGenetik.setBackgroundColor(requireContext().getColor(R.color.lighest_gray))
            binding.btnOnkoGenetik.setTextColor(requireContext().getColor(R.color.purple))

            setupAnalyse(viewModel.covid)
        }
        binding.btnOnkoGenetik.setOnClickListener {
            binding.btnOnkoGenetik.setBackgroundColor(requireContext().getColor(R.color.blue))
            binding.btnOnkoGenetik.setTextColor(requireContext().getColor(R.color.white))

            binding.btnPopular.setBackgroundColor(requireContext().getColor(R.color.lighest_gray))
            binding.btnPopular.setTextColor(requireContext().getColor(R.color.purple))
            binding.btnCovid.setBackgroundColor(requireContext().getColor(R.color.lighest_gray))
            binding.btnCovid.setTextColor(requireContext().getColor(R.color.purple))

            setupAnalyse(viewModel.onkogenetik)
        }
        if(navArgs.bool) {
            binding.trash.visibility = View.INVISIBLE
        } else {
            binding.trash.visibility = View.VISIBLE
        }
        binding.trash.setOnClickListener {
            var names = ""
            var prices = ""
            for (i in viewModel.trash.value) {
                names += "${i.name}%$%"
                prices += "${i.price}%$%"
            }
            findNavController().navigate(
                AnalysesFragmentDirections.actionAnalysesFragmentToTrashFragment(
                    names,
                    prices,
                    binding.tvPrice.text.split(" ")[0].toInt()
                )
            )
        }
        lifecycleScope.launchWhenStarted {
            viewModel.news.collect {
                setupNews(it)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.analyses.collect {
                setupAnalyse(it)
                viewModel.setupCategory()
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.size.collect {
                var price = 0
                for (i in viewModel.trash.value) {
                    price += i.price.toInt()
                }
                if (price > 0) {
                    binding.trash.visibility = View.VISIBLE
                    binding.tvPrice.text = getString(R.string.count, price.toString())
                } else {
                    binding.trash.visibility = View.INVISIBLE
                    binding.tvPrice.text = getString(R.string.count, "0")
                }
            }
        }
    }

    private fun setupNews(news: List<News>) = with(binding) {
        rvNews.adapter = NewsAdapter(news, requireContext())
    }

    private fun setupAnalyse(analyses: List<Analyse>) = with(binding) {
        rvAnalyses.adapter = AnalyseAdapter(analyses, requireContext()) { analyse, bool ->
            if (bool) {
                showDialog(analyse)
            } else {
                if (viewModel.trash.value.remove(analyse)) {
                    viewModel.size.value -= 1
                }
            }
        }
    }


    private fun showDialog(analyse: Analyse) {
        val dialog = AnalyseDialogFragment(analyse) {
            if (viewModel.trash.value.add(analyse)) {
                viewModel.size.value += 1
            }
        }
        dialog.show(requireActivity().supportFragmentManager, "Analyse")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnalysesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}