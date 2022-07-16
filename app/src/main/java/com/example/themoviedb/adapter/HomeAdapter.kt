package com.example.themoviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.themoviedb.R
import com.example.themoviedb.data.remote.model.popular.Result
import com.example.themoviedb.databinding.FragmentCardBinding
import com.example.themoviedb.view.HomeDirections


class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val difCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FragmentCardBinding.bind(view)
        private var background = RequestOptions().placeholder(R.color.black)

        fun bind(dataPopular: Result) {
            binding.apply {
                binding.tvTittle.text = dataPopular.title
                binding.tvDate.text = dataPopular.releaseDate
                binding.progressBar.text = "${(dataPopular.voteAverage!! *10).toInt()}%"
                binding.indicator.progress = (dataPopular.voteAverage*10).toInt()
                Glide.with(itemView.context)
                    .load("https://www.themoviedb.org/t/p/w220_and_h330_face/${dataPopular.posterPath}")
                    .apply(background).into(image)
                root.setOnClickListener {
                    val action = HomeDirections.actionHomeToDetail(dataPopular.id!!)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    private val differ = AsyncListDiffer(this,difCallback)

    fun updateData(results : List<Result>) = differ.submitList(results)

    override fun getItemCount(): Int = differ.currentList.size
}