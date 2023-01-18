package com.example.taskapp.ui.onBoarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.databinding.ItemOnboardingBinding
import com.example.taskapp.model.OnBoard
import com.example.taskapp.utils.loadImage

class OnBoardingAdapter(private val onClick: () -> Unit): Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    private val onBoardData = arrayListOf(
        OnBoard("All of your tasks at one place", "Organise your tasks freely without any difficulties.", "https://cutewallpaper.org/cdn-cgi/mirage/dd19f2d06ebc24f541f142b37b4289ffa7de722a7607e39984c5c6dd4ce8defd/1280/24/study-png/learning-hd-png-download-vector-study-from-home-transparent-png--kindpng.png"),
        OnBoard("Don't miss your exciting events", "Set notifications so you don't forget about any of your events, easy peasy!", "https://static.vecteezy.com/ti/vecteur-libre/p3/3023597-etude-femme-heureuse-gratuit-vectoriel.jpg"),
        OnBoard("Save your time!", "By using our app you can easily note all of your tasks and events in seconds.", "https://www.pngall.com/wp-content/uploads/12/Illustration-PNG-Images.png")
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
            binding.image.loadImage(onBoard.image.toString())
            binding.btnStart.isVisible = adapterPosition == onBoardData.lastIndex
            binding.btnStart.setOnClickListener{
                onClick()
            }
        }
    }
}