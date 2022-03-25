package com.example.android.simpsonfortniteproject.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.android.simpsonfortniteproject.R
import com.example.android.simpsonfortniteproject.databinding.SignUpFragmentBinding
import com.example.android.simpsonfortniteproject.ui.login.onTextChanged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = SignUpFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.signUpTextFieldUsernameInput.onTextChanged { text ->
            viewModel.setUsername(text)
        }

        binding.signUpTextFieldPasswordInput.onTextChanged { text ->
            viewModel.setPassword(text)
        }

        viewModel.canSignUp.observe(viewLifecycleOwner) { bool ->
            binding.signUpButton.isEnabled = bool
            if(bool)
            {
                binding.signUpButton.alpha = 1.0F
            }
            else
            {
                binding.signUpButton.alpha = 0.3F
            }
        }

        binding.signUpButton.setOnClickListener { v: View ->
            viewModel.signUpUser(binding.signUpTextFieldPasswordInput.text.toString())

            v.findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
            )
        }

        return binding.root
    }

}
