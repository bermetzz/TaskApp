package com.example.taskapp.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.R
import com.example.taskapp.databinding.ItemOnboardingBinding
import com.example.taskapp.model.OnBoard

class OnBoardingAdapter(private val onClick: () -> Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val onBoardData = arrayListOf(
        OnBoard("All of your tasks at one place", "Organise your tasks freely without any difficulties.", R.raw.cat),
        OnBoard("Don't miss exciting events", "Set notifications so you don't forget about any of your events, easy peasy!", R.raw.catwriter),
        OnBoard("Save your time!", "By using our app you can easily note all of your tasks and events in seconds.", R.raw.catloader)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(onBoardData[position])
    }

    override fun getItemCount(): Int {
        return onBoardData.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) : ViewHolder(binding.root){
        fun bind(onBoard: OnBoard) {
            binding.tvTitle.text = onBoard.title
            binding.tvDescription.text = onBoard.description
            onBoard.image?.let { binding.image.setAnimation(it) }
            binding.btnStart.isVisible = adapterPosition == onBoardData.lastIndex
            binding.btnStart.setOnClickListener{
                onClick()
            }
        }
    }
}