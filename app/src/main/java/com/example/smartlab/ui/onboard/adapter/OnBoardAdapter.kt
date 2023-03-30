package com.example.smartlab.ui.onboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartlab.R
import com.example.smartlab.databinding.ItemOnBoardBinding
import com.example.smartlab.ui.onboard.model.OnBoardStep

class OnBoardAdapter :
    RecyclerView.Adapter<OnBoardAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(val binding: ItemOnBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OnBoardStep) {
            binding.tvHeader.text = item.header
            binding.tvDescription.text = item.description
            binding.ivLogo.setImageResource(item.resId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding =
            ItemOnBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int = steps.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(steps[position])

    }

    companion object {
        private val steps = listOf(
            OnBoardStep("Анализы", "Экспресс сбор и получение проб", R.drawable.analises),
            OnBoardStep("Уведомления", "Вы быстро узнаете о результатах", R.drawable.notification),
            OnBoardStep(
                "Мониторинг",
                "Наши врачи всегда наблюдают за вашими показателями здоровья",
                R.drawable.monitoring
            )
        )
    }
}