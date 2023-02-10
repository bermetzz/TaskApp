package com.example.taskapp.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskapp.R
import com.example.taskapp.data.Pref
import com.example.taskapp.databinding.FragmentProfileBinding
import com.example.taskapp.utils.loadImage
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var pref: Pref
    private lateinit var image: String
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri: Uri? ->
            image = uri.toString()
            binding.profileImage.loadImage(image)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        binding.apply {
            etName.setText(pref.getName())
            etAge.setText(pref.getAge())
            profileImage.loadImage(pref.getImage())
        }
        binding.exit.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.authFragment)
        }
        binding.profileImage.setOnClickListener{
            getContent.launch("image/*")
        }
        binding.save.setOnClickListener {
            pref.saveName(binding.etName.text.toString())
            pref.saveAge(binding.etAge.text.toString())
            pref.saveImage(image)
        }
    }
}