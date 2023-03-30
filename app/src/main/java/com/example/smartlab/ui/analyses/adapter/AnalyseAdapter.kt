package com.example.smartlab.ui.analyses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Analyse
import com.example.smartlab.R
import com.example.smartlab.databinding.ItemAnalyseBinding

class AnalyseAdapter(private val analyses: List<Analyse>, private val context: Context, private val showAnalyse: (Analyse, Boolean) -> Unit) :
    RecyclerView.Adapter<AnalyseAdapter.AnalysViewHolder>() {

    inner class AnalysViewHolder(val binding: ItemAnalyseBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(analyse: Analyse) = with(binding) {
            btnAdd.text = "Добавить"
            btnAdd.setTextColor(context.getColor(R.color.white))
            btnAdd.setBackgroundColor(context.getColor(R.color.blue))
            tvAnalyseName.text = analyse.name
            tvTime.text = analyse.time_result
            tvPrice.text = context.getString(R.string.count, analyse.price)
            btnAdd.setOnClickListener {
                if(btnAdd.text == "Добавить") {
                    showAnalyse(analyse, true)
                    btnAdd.text = "Убрать"
                    btnAdd.setTextColor(context.getColor(R.color.blue))
                    btnAdd.setBackgroundColor(context.getColor(R.color.white))
                } else {
                    showAnalyse(analyse, false)
                    btnAdd.text = "Добавить"
                    btnAdd.setTextColor(context.getColor(R.color.white))
                    btnAdd.setBackgroundColor(context.getColor(R.color.blue))
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysViewHolder {
        return AnalysViewHolder(
            ItemAnalyseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = analyses.size


    override fun onBindViewHolder(holder: AnalysViewHolder, position: Int) {
        holder.onBind(analyses[position])
    }
}