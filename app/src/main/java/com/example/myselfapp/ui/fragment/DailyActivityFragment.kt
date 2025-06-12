package com.example.myselfapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myselfapp.databinding.FragmentDailyActivityBinding
import com.example.myselfapp.ui.adapter.DailyActivityAdapter
import com.example.myselfapp.ui.adapter.FriendsListAdapter
import com.example.myselfapp.ui.viewmodel.DailyActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DailyActivityFragment : Fragment() {

    private var _binding: FragmentDailyActivityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DailyActivityViewModel by viewModels()

    private lateinit var dailyActivityAdapter: DailyActivityAdapter
    private lateinit var friendsListAdapter: FriendsListAdapter // Adapter baru untuk teman

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews() // Perbarui nama fungsi untuk mencerminkan kedua RecyclerView
        observeViewModel()

        // Muat data aktivitas harian dan teman
        viewModel.loadDailyActivitiesAndFriends()
    }

    private fun setupRecyclerViews() {
        // Setup RecyclerView untuk Aktivitas Harian
        dailyActivityAdapter = DailyActivityAdapter(emptyList())
        binding.rvDailyActivities.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyActivityAdapter
        }

        // Setup RecyclerView untuk Daftar Teman (Horizontal)
        friendsListAdapter = FriendsListAdapter(emptyList())
        binding.rvFriendsList.apply { // rv_friends_list harus ada di layout XML
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = friendsListAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.dailyActivities.observe(viewLifecycleOwner) { activities ->
            activities?.let {
                dailyActivityAdapter = DailyActivityAdapter(it)
                binding.rvDailyActivities.adapter = dailyActivityAdapter
            }
        }

        viewModel.friendsList.observe(viewLifecycleOwner) { friends ->
            friends?.let {
                friendsListAdapter = FriendsListAdapter(it)
                binding.rvFriendsList.adapter = friendsListAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}