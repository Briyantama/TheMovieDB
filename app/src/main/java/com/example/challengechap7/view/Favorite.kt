package com.example.challengechap7.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.challengechap7.adapter.FavoriteAdapter
import com.example.challengechap7.databinding.FragmentFavoriteBinding
import com.example.challengechap7.di.myapp.Injector
import com.example.challengechap7.view.viewmodel.FavoriteViewModel
import com.example.challengechap7.view.viewmodelfactory.FavoriteViewModelFactory
import javax.inject.Inject

class Favorite : Fragment() {

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels {
        favoriteViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as Injector).createFavoriteSubComponent().inject(this)

        viewModel.getUser()

        val adapter = FavoriteAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.getListFavorite.observe(viewLifecycleOwner) { favoriteList ->
            adapter.updateData(favoriteList)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}