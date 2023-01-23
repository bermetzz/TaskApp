package com.example.taskapp.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.taskapp.data.Pref
import com.example.taskapp.databinding.FragmentOnBoardingBinding
import com.example.taskapp.ui.onBoarding.adapter.OnBoardingAdapter

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        val adapter = OnBoardingAdapter() {
            pref.saveSeen()
            findNavController().navigateUp()
        }
        binding.viewpager.adapter = adapter
        binding.indicator.setViewPager(binding.viewpager)
        adapter.registerAdapterDataObserver(binding.indicator.adapterDataObserver)
    }

}