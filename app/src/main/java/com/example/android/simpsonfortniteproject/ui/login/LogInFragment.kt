package com.example.android.simpsonfortniteproject.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.android.simpsonfortniteproject.R
import com.example.android.simpsonfortniteproject.databinding.LogInFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = LogInFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.logInTV.setOnClickListener { v: View ->
            v.findNavController().navigate(
                LogInFragmentDirections
                    .actionLogInFragmentToMainFragment()
            )
        }

        binding.textFieldUsernameInput
            .onTextChanged{text ->
                viewModel.setUsername(text)
            }

        binding.textFieldPasswordInput
            .onTextChanged { text ->
                viewModel.setPassword(text)
            }

        viewModel.canLogIn.observe(viewLifecycleOwner) { bool ->
            binding.logInButton.isEnabled = bool
            if (bool) {
                binding.logInButton.alpha = 1.0F
            } else {
                binding.logInButton.alpha = 0.3F
            }
        }

        binding.logInButton.setOnClickListener { v: View ->
            if (viewModel.checkValidity())
            {
                viewModel.logInUser(binding.textFieldPasswordInput.text.toString())
                viewModel.gotUser.observe(viewLifecycleOwner) { bool ->
                    if (bool == false)
                    {
                        Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show()
                        viewModel.doneLogginIn()
                    }
                    else if(bool == true)
                    {
                        v.findNavController().navigate(
                            LogInFragmentDirections
                                .actionLogInFragmentToMainFragment()
                        )
                        viewModel.doneLogginIn()
                    }
                }
            }
        }



        binding.logInSignUpButton.setOnClickListener { v: View ->
            v.findNavController().navigate(
                LogInFragmentDirections
                    .actionLogInFragmentToSignUpFragment()
            )
        }

        return binding.root
    }

}

fun TextInputEditText.onTextChanged(
    afterChanged: (text: String?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(editable: Editable) {
            afterChanged(editable.toString())

        }
    })
}
