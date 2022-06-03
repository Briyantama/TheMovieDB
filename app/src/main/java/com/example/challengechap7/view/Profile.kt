package com.example.challengechap7.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.challengechap7.R
import com.example.challengechap7.data.local.auth.User
import com.example.challengechap7.databinding.FragmentProfileBinding
import com.example.challengechap7.di.myapp.Injector
import com.example.challengechap7.resource.Status
import com.example.challengechap7.view.viewmodel.UpdateProfileViewModel
import com.example.challengechap7.view.viewmodelfactory.UpdateViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Profile : Fragment() {

    @Inject
    lateinit var updateViewModelFactory: UpdateViewModelFactory
    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var dateListener: DatePickerDialog.OnDateSetListener
    private val calendar = Calendar.getInstance()
    private val viewModel: UpdateProfileViewModel by viewModels{
        updateViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.application as Injector).createUpdateSubComponent().inject(this)

        val userData = User()

        viewModel.getIdUser().observe(viewLifecycleOwner) {
            viewModel.userData(it)
        }

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            when (user.status) {
                Status.SUCCESS -> {
                    if (user.data != null) {
                        userData.id = user.data.id

                        userData.password = user.data.password
                        binding.edPass.setText(user.data.password)
                        binding.edPassX.setText(user.data.password)

                        userData.fullname = user.data.fullname
                        binding.edNama.setText(user.data.fullname)

                        userData.username = user.data.username
                        binding.edUser.setText(user.data.username)

                        userData.ttl = user.data.ttl
                        binding.edTanggal.setText(user.data.ttl)

                        userData.address = user.data.address
                        binding.edAlamat.setText(user.data.address)

                        userData.email = user.data.email
                        binding.edEmail.setText(user.data.email)

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

        binding.logout.setOnClickListener {
            val dialog = AlertDialog.Builder(view.context)
            dialog.setTitle("Logout")
            dialog.setMessage("Apakah Anda Yakin Ingin Logout ?")
            dialog.setPositiveButton("Yakin") { _, _ ->
                Snackbar.make(binding.root, "User Berhasil Logout", Snackbar.LENGTH_LONG)
                    .show()
                viewModel.clearDataUser()
                Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_login)
            }
            dialog.setNegativeButton("Batal") { listener, _ ->
                listener.dismiss()
            }
            dialog.show()
        }

        dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        binding.edTanggal.setOnClickListener {
            DatePickerDialog(
                view.context,
                dateListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.update.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val username = binding.edUser.text.toString()
            val fullname = binding.edNama.text.toString()
            val ttl = binding.edTanggal.text.toString()
            val address = binding.edAlamat.text.toString()
            val password = binding.edPass.text.toString()
            val id = userData.id

            val user = User(
                id = id,
                email = email,
                username = username,
                fullname = fullname,
                ttl = ttl,
                address = address,
                password = password,
            )
            viewModel.update(user)
        }

        viewModel.saved.observe(viewLifecycleOwner) {
            val check = it.getContentIfNotHandled() ?: return@observe
            if (check) {
                val dialog = AlertDialog.Builder(view.context)
                dialog.setTitle("Update User")
                dialog.setMessage("Apakah Anda Yakin Ingin Update Data User ?")
                dialog.setPositiveButton("Yakin") { _, _ ->
                    Snackbar.make(binding.root, "User Berhasil Diupdate",
                        Snackbar.LENGTH_LONG).show()
                    Navigation.findNavController(requireView()).navigateUp()
                }

                dialog.setNegativeButton("Batal") { listener, _ ->
                    listener.dismiss()
                }

                dialog.show()

            } else {
                Snackbar.make(binding.root, "User Gagal Diupdate",
                    Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.edTanggal.setText(sdf.format(calendar.time).toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}