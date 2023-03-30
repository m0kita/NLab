package com.example.smartlab.ui.analyses.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Analyse
import com.example.smartlab.R
import com.example.smartlab.databinding.ItemAnalyseBinding
import com.example.smartlab.databinding.ItemTrashBinding

class TrashAdapter(
    private val names: MutableList<String>,
    private val prices: MutableList<String>,
    private val context: Context,
    private val back: () -> Unit
) :
    RecyclerView.Adapter<TrashAdapter.TrashViewHolder>() {
    private var allPrice = 0

    inner class TrashViewHolder(val binding: ItemTrashBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(name: String, price: String) = with(binding) {
            tvAnalyseName.text = name
            allPrice += price.toInt()
            tvPrice.text = context.getString(R.string.count, price)
            tvPacient.text = context.getString(R.string.pacient, "1")
            btnPlus.setOnClickListener {
                val count = tvPacient.text.split(" ")
                val pacient = count[0].toInt() + 1
                binding.tvPacient.text = context.getString(R.string.pacient, pacient.toString())
            }
            btnMinus.setOnClickListener {
                val count = tvPacient.text.split(" ")
                if(count[0].toInt() != 1) {
                    val pacient = count[0].toInt() - 1
                    binding.tvPacient.text = context.getString(R.string.pacient, pacient.toString())
                }
            }
            ivClose.setOnClickListener {
                names.remove(name)
                prices.remove(price)
                notifyDataSetChanged()
                if(names.size == 1) {
                    back()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrashViewHolder {
        return TrashViewHolder(
            ItemTrashBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = names.size - 1

    override fun onBindViewHolder(holder: TrashViewHolder, position: Int) {
            holder.onBind(names[position], prices[position])
    }
}