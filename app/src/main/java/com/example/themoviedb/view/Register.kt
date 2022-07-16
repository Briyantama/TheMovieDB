package com.example.themoviedb.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.themoviedb.databinding.FragmentRegisterBinding
import com.example.themoviedb.di.myapp.Injector
import com.example.themoviedb.view.viewmodel.RegisterViewModel
import com.example.themoviedb.view.viewmodelfactory.RegisterViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Register : Fragment() {

    @Inject
    lateinit var registerViewModelFactory: RegisterViewModelFactory
    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RegisterViewModel by viewModels {
        registerViewModelFactory
    }
    private lateinit var dateListener: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as Injector).createRegisterSubComponent().inject(this)

        dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        binding.edTanggalLahir.setOnClickListener {
            DatePickerDialog(
                view.context,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        updateDateInView()

        binding.confirm.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val username = binding.edUser.text.toString()
            val fullname = binding.edNama.text.toString()
            val ttl = binding.edTanggalLahir.text.toString()
            val address = binding.edAlamat.text.toString()
            val password = binding.edPass.text.toString()
            viewModel.save(email, username, fullname, ttl, address, password)
        }

        viewModel.saved.observe(viewLifecycleOwner) {
            val check = it.getContentIfNotHandled() ?: return@observe
            if (check) {
                Snackbar.make(binding.root, "User Berhasil Dibuat", Snackbar.LENGTH_LONG).show()
                findNavController().navigateUp()
            } else {
                Snackbar.make(binding.root, "User Gagal Dibuat", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.edTanggalLahir.setText(sdf.format(calendar.time).toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}