package com.example.loveapp.ui.fragments.onboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.loveapp.R
import com.example.loveapp.databinding.FragmentOnBoardBinding
import com.example.loveapp.application.SharedPreferencesHelper
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardFragment : Fragment() {

    @Inject
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
    }

    private fun initialize() {
        val viewPager2 = binding.viewPager2
        viewPager2.adapter = OnBoardAdapterFragment(this)

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
        }.attach()
    }

    private fun setupListener() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 3) {
                    binding.btnStart.visibility = View.VISIBLE
                    binding.tvTitle.visibility = View.VISIBLE
                } else {
                    binding.btnStart.visibility = View.INVISIBLE
                    binding.tvTitle.visibility = View.INVISIBLE
                }
            }
        })

        binding.btnStart.setOnClickListener {
            sharedPreferencesHelper.setOnBoardShown()
            findNavController().navigate(R.id.action_onBoardFragment_to_loveCalculatorFragment)
        }
    }
}
