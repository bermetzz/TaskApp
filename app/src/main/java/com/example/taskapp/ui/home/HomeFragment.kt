package com.example.taskapp.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.App
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentHomeBinding
import com.example.taskapp.model.Task
import com.example.taskapp.ui.home.adapter.TaskAdapter
import com.example.taskapp.utils.isOnline
import com.example.taskapp.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: TaskAdapter
    private val db = Firebase.firestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this::onClick, this::onItemClick)
    }

    private fun onClick(task: Task) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            App.db.taskDao().delete(task)
            showToast("Task is successfully removed")
            setData()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }
        builder.setTitle("Delete task")
        builder.setMessage("Are you sure you want to delete the task?")
        builder.create().show()
    }

    private fun onItemClick(task: Task) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().isOnline()) {
            getTasks()
        } else {
            val tasks = App.db.taskDao().getAll()
            adapter.addTasks(tasks)

        }
//        setData()
        binding.recyclerView.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun getTasks() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            db.collection(uid).get().addOnSuccessListener {
                val data = it.toObjects(Task::class.java)
                adapter.addTasks(data)
                Log.e("bzz", "getTasks: $data")
            }.addOnFailureListener {}
        }
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addTasks(tasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}