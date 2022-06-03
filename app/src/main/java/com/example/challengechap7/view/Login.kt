package com.example.challengechap7.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.challengechap7.R
import com.example.challengechap7.databinding.FragmentLoginBinding
import com.example.challengechap7.di.myapp.Injector
import com.example.challengechap7.resource.Status
import com.example.challengechap7.view.viewmodel.LoginViewModel
import com.example.challengechap7.view.viewmodelfactory.LoginViewModelFactory
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class Login : Fragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels{
        loginViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as Injector).createLoginSubComponent().inject(this)

        binding.login.setOnClickListener {
            viewModel.login(
                binding.edEmail.text.toString(),
                binding.edPass.text.toString()
            )

            viewModel.loginStatus.observe(viewLifecycleOwner) {user ->
                when (user.status) {
                    Status.SUCCESS -> {
                        if (user.data != null) {
                            viewModel.saveUserDataStore(true, user.data.id)
                            Navigation.findNavController(requireView()).navigate(R.id.action_login_to_home)
                        } else {
                            Snackbar.make(
                                binding.root,
                                "User Tidak Ditemukan",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), user.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }

        binding.register.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_login_to_register)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}