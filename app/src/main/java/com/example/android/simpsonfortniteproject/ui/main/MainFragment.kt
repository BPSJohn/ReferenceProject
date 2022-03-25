package com.example.android.simpsonfortniteproject.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.android.simpsonfortniteproject.R
import com.example.android.simpsonfortniteproject.databinding.MainFragmentBinding
import com.example.android.simpsonfortniteproject.ui.camera.CameraActivity
import com.example.android.simpsonfortniteproject.ui.geofencing.GeofencingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        binding.cameraMessage.setOnClickListener {
            val intent = Intent(activity, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.geofencingTV.setOnClickListener{
            val intent = Intent(activity, GeofencingActivity::class.java)
            startActivity(intent)
        }

        binding.sharedPreferencesTV.setOnClickListener {v: View ->
            v.findNavController().navigate(
                MainFragmentDirections
                    .actionMainFragmentToSharedPreferencesFragment()
            )
        }

        return binding.root
    }

}
