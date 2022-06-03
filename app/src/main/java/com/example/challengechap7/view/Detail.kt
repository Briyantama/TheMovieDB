package com.example.challengechap7.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.challengechap7.R
import com.example.challengechap7.data.local.favorite.MovieEntity
import com.example.challengechap7.databinding.FragmentDetailBinding
import com.example.challengechap7.di.myapp.Injector
import com.example.challengechap7.view.viewmodel.DetailViewModel
import com.example.challengechap7.view.viewmodelfactory.DetailViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class Detail : Fragment() {

    @Inject
    lateinit var detailViewModelFactory: DetailViewModelFactory
    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels {
        detailViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as Injector).createDetailSubComponent().inject(this)
        viewModel.getDetail(args.data)

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.pbLoading.visibility = View.GONE
            }
        }

        viewModel.detail.observe(viewLifecycleOwner) { movieDetail ->

            Glide.with(requireContext())
                .load("https://www.themoviedb.org/t/p/w220_and_h330_face/${movieDetail!!.posterPath}")
                .into(binding.poster)
            binding.judul.text = movieDetail.title
            binding.dateYear.text = "(${movieDetail.releaseDate!!.take(4)})"
            binding.tvDate.text = movieDetail.releaseDate
            binding.pbLoading.progress = (movieDetail.voteAverage!! * 10).toInt()
            binding.tvDetail.text = "${(movieDetail.voteAverage * 10).toInt()}%"
            binding.detail.text = movieDetail.overview

            val movieEntity = MovieEntity(
                movieDetail.backdropPath,
                movieDetail.id!!,
                movieDetail.overview,
                movieDetail.posterPath,
                movieDetail.releaseDate,
                movieDetail.title,
                movieDetail.voteAverage,
                movieDetail.voteCount
            )

            viewModel.showUserIsFavorite(movieEntity)

            binding.btnFavorite.setOnClickListener {
                viewModel.checkFavoriteUser(movieEntity)
            }
        }

        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.btnFavorite.context,
                        R.drawable.ic_favorite_true
                    )
                )
            } else {
                binding.btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                        binding.btnFavorite.context,
                        R.drawable.ic_favorite_false
                    )
                )
            }
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