package com.example.taskapp.ui.task

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.model.Task
import com.example.taskapp.databinding.FragmentTaskBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    private val db = Firebase.firestore
    private lateinit var navArgs: TaskFragmentArgs
    private var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            navArgs = TaskFragmentArgs.fromBundle(it)
            task = navArgs.task
        }

        if (task != null) {
            binding.etTitle.setText(task?.title)
            binding.etDescription.setText(task?.description)
            binding.btnSave.text = getString(R.string.update)
        } else {
            binding.btnSave.text = getString(R.string.saveTask)
        }

        binding.btnSave.setOnClickListener {
            if (task != null) onUpdate()
            else onSave()
        }
    }

    private fun onSave() {
        val task = Task(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        putTask(task)
        App.db.taskDao().insert(task)
        findNavController().navigateUp()
    }

    private fun onUpdate() {
        task?.title = binding.etTitle.text.toString()
        task?.description = binding.etDescription.text.toString()
        task?.let { App.db.taskDao().update(it) }
        findNavController().navigateUp()
    }

    private fun putTask(task: Task) {
        FirebaseAuth.getInstance().currentUser?.let { it ->
            db.collection(it.uid).add(task).addOnSuccessListener {
                Log.e("bzz", "onSave: success!")
            }.addOnFailureListener {
                Log.e("bzz", "onSave: " + it.message)
            }
        }
    }
}
