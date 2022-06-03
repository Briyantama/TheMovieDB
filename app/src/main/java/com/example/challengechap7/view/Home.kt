package com.example.challengechap7.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.challengechap7.R
import com.example.challengechap7.databinding.FragmentHomeBinding
import com.example.challengechap7.di.myapp.Injector
import com.example.challengechap7.resource.Status
import com.example.challengechap7.adapter.HomeAdapter
import com.example.challengechap7.view.viewmodel.HomeViewModel
import com.example.challengechap7.view.viewmodelfactory.HomeViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class Home : Fragment() {

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels{
        homeViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeAdapter()
        (activity?.application as Injector).createHomeSubComponent().inject(this)

        viewModel.getIdUser().observe(viewLifecycleOwner) {
            viewModel.userData(it)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            when (user.status) {
                Status.SUCCESS -> {
                    if (user.data != null) {
                        binding.username.text = "Welcome, ${user.data.username}"
                    } else {
                        Snackbar.make(
                            binding.root,
                            "User Tidak Ditemukan",
                            Snackbar.LENGTH_LONG
                        )
                            .show()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), user.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE
            }
        }

        viewModel.popular.observe(viewLifecycleOwner) {
            adapter.updateData(it)
            Log.d("HomeFragment", it.toString())
        }

        binding.profile.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_home_to_profile)
        }

        binding.favorite.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_home_to_favorite)
        }

        viewModel.errorStatus.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(binding.root, text, Snackbar.LENGTH_LONG).show()
                viewModel.onSnackbarShown()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}