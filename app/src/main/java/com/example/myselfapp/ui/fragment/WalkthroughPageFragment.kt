package com.example.myselfapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myselfapp.R // Pastikan import R benar
import com.example.myselfapp.databinding.FragmentWalkthroughPageBinding // Kelas binding untuk layout halaman walkthrough
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WalkthroughPageFragment : Fragment() {

    private var _binding: FragmentWalkthroughPageBinding? = null
    private val binding get() = _binding!!

    // Companion object untuk membuat instance fragment dengan argumen
    companion object {
        private const val ARG_PAGE_NUMBER = "page_number"

        /**
         * Membuat instance baru dari WalkthroughPageFragment.
         * @param pageNumber Nomor halaman (0, 1, 2, dst.).
         */
        fun newInstance(pageNumber: Int): WalkthroughPageFragment {
            val fragment = WalkthroughPageFragment()
            val args = Bundle()
            args.putInt(ARG_PAGE_NUMBER, pageNumber)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalkthroughPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pageNumber = arguments?.getInt(ARG_PAGE_NUMBER) ?: 0

        // Atur konten halaman berdasarkan nomor halaman
        when (pageNumber) {
            0 -> {
                binding.ivWalkthroughImage.setImageResource(R.drawable.ic_walkthrough_1)
                binding.tvWalkthroughTitle.text = "Selamat Datang di MySelfApp!"
                binding.tvWalkthroughDescription.text = "Aplikasi personal Anda untuk mencatat dan berbagi tentang diri Anda."
            }
            1 -> {
                binding.ivWalkthroughImage.setImageResource(R.drawable.ic_walkthrough_2)
                binding.tvWalkthroughTitle.text = "Jelajahi Diri Anda"
                binding.tvWalkthroughDescription.text = "Catat aktivitas harian, minat, dan galeri pribadi Anda."
            }
            2 -> {
                binding.ivWalkthroughImage.setImageResource(R.drawable.ic_walkthrough_3)
                binding.tvWalkthroughTitle.text = "Terhubung dengan Dunia"
                binding.tvWalkthroughDescription.text = "Temukan lokasi Anda, kontak, dan tautan media sosial."
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}