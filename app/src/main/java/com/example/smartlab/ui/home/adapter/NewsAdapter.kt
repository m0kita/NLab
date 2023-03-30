package com.example.smartlab.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.News
import com.example.smartlab.R
import com.example.smartlab.databinding.FragmentHomeBinding
import com.example.smartlab.databinding.ItemNewsBinding

class NewsAdapter(private val news: List<News>, private val context: Context) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(news: News) = with(binding) {
            tvNews.text = news.name
            tvDescription.text = news.description
            tvCount.text = context.getString(R.string.count, news.price)
            Glide.with(binding.root).load(news.image).into(binding.ivNews)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(news[position])
    }
}