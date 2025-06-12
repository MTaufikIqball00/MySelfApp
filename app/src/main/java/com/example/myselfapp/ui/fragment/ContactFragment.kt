package com.example.myselfapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myselfapp.R
import com.example.myselfapp.databinding.FragmentContactBinding
import com.example.myselfapp.ui.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe data dari ViewModel
        viewModel.phoneNumber.observe(viewLifecycleOwner) { phoneNumber ->
            binding.tvPhoneNumber.text = phoneNumber
            binding.btnCallPhone.setOnClickListener {
                callPhoneNumber(phoneNumber)
            }
        }

        viewModel.emailAddress.observe(viewLifecycleOwner) { emailAddress ->
            binding.tvEmailAddress.text = emailAddress
            binding.btnSendEmail.setOnClickListener {
                sendEmail(emailAddress)
            }
        }

        viewModel.socialMediaLinks.observe(viewLifecycleOwner) { links ->
            // Menyiapkan OnClickListener untuk setiap tombol media sosial
            binding.btnInstagram.setOnClickListener {
                val instagramUrl = links["Instagram"]
                if (!instagramUrl.isNullOrEmpty()) {
                    openUrlInBrowser(instagramUrl)
                } else {
                    Toast.makeText(context, "Tautan Instagram tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }

            binding.btnLinkedin.setOnClickListener {
                val linkedinUrl = links["LinkedIn"]
                if (!linkedinUrl.isNullOrEmpty()) {
                    openUrlInBrowser(linkedinUrl)
                } else {
                    Toast.makeText(context, "Tautan LinkedIn tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }

            binding.btnGithub.setOnClickListener {
                val githubUrl = links["GitHub"]
                if (!githubUrl.isNullOrEmpty()) {
                    openUrlInBrowser(githubUrl)
                } else {
                    Toast.makeText(context, "Tautan GitHub tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }

            // Opsional: Perbarui teks TextView dengan username/nama platform
            binding.tvInstagramUsername.text = "Instagram"
            binding.tvLinkedinUsername.text = "LinkedIn"
            binding.tvGithubUsername.text = "GitHub"

            binding.btnFindMe.setOnClickListener {
                // Mengarahkan ke FindMeFragment menggunakan Navigation Component
                findNavController().navigate(R.id.findMeFragment)
            }
        }
    }

    private fun callPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Tidak dapat melakukan panggilan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendEmail(emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Hanya aplikasi email yang akan merespons
            putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            putExtra(Intent.EXTRA_SUBJECT, "Pertanyaan dari MySelfApp")
        }
        try {
            startActivity(Intent.createChooser(intent, "Kirim Email Menggunakan"))
        } catch (e: Exception) {
            Toast.makeText(context, "Tidak ada aplikasi email yang terinstal", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Tidak dapat membuka tautan: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}