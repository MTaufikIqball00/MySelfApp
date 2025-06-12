package com.example.myselfapp.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.myselfapp.R
import com.example.myselfapp.databinding.DialogAboutBinding
import com.example.myselfapp.ui.viewmodel.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutDialogFragment : DialogFragment() {

    private var _binding: DialogAboutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AboutViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)// Opsional: tambahkan background bulat
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.appName.observe(viewLifecycleOwner) {
            binding.tvAppName.text = it
        }
        viewModel.appVersion.observe(viewLifecycleOwner) {
            binding.tvAppVersion.text = "Versi: $it"
        }
        viewModel.developerName.observe(viewLifecycleOwner) {
            binding.tvDeveloperName.text = "$it"
        }
        viewModel.aboutText.observe(viewLifecycleOwner) {
            binding.tvAboutText.text = it
        }

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}