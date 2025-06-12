package com.example.myselfapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.semantics.text
import com.example.myselfapp.R
import com.example.myselfapp.databinding.FragmentWalkthroughBinding
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myselfapp.databinding.FragmentWalkthroughPageBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WalkthroughFragment : Fragment() {

    private var _binding: FragmentWalkthroughBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalkthroughBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Siapkan ViewPager2
        binding.viewPager.adapter = WalkthroughPagerAdapter(this)

        // --- BARU: Hubungkan TabLayout ke ViewPager2 menggunakan TabLayoutMediator ---
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Anda bisa mengatur teks tab atau ikon di sini jika tab bukan hanya indikator
            // Untuk indikator titik, kita tidak perlu melakukan apa-apa di sini karena
            // tampilan titik diatur sepenuhnya oleh app:tabBackground di XML.
        }.attach()
        // --- AKHIR KONEKSI TABLAYOUT ---

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < (binding.viewPager.adapter?.itemCount ?: 0) - 1) {
                binding.viewPager.currentItem = currentItem + 1
            } else {
                // Halaman terakhir, navigasi ke Home
                findNavController().navigate(R.id.action_walkthroughFragment_to_homeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Adapter untuk ViewPager2 (tidak berubah dari sebelumnya)
    private inner class WalkthroughPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 3 // 3 halaman walkthrough

        override fun createFragment(position: Int): Fragment {
            return WalkthroughPageFragment.newInstance(position)
        }
    }
}