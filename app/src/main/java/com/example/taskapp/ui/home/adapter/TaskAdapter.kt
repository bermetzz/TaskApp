package com.example.taskapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.taskapp.model.Task
import com.example.taskapp.databinding.ItemTaskBinding

class TaskAdapter(val onClick: (task: Task) -> Unit):
    Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()


    fun addTask(task: Task){
        data.add(0, task)
        notifyItemChanged(0)
    }
    fun addTasks(list: List<Task>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : ViewHolder(binding.root){
        fun bind(task: Task) {
            binding.apply {
                tvTitle.text = task.title
                tvDescription.text = task.description
            }
            itemView.setOnLongClickListener{
                onClick(task)
                notifyItemChanged(adapterPosition)
                true
            }
        }
    }
}