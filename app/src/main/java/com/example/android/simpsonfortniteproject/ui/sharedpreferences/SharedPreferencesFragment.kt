package com.example.android.simpsonfortniteproject.ui.sharedpreferences

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.android.simpsonfortniteproject.databinding.SharedPreferencesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SharedPreferencesFragment : Fragment() {

    private val viewModel: SharedPreferencesViewModel by viewModels()

    private var name: EditText? = null

    private var age: EditText? = null

    lateinit var sharedPreferences: SharedPreferences

    lateinit var masterKey: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = SharedPreferencesFragmentBinding.inflate(inflater)

        masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        sharedPreferences = EncryptedSharedPreferences.create(
            "MySharedPref",
            masterKey,
            requireContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        //"MySharedPref"

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        name = binding.edit1

        age = binding.edit2

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // Fetching the stored data
        // from the SharedPreference

        val s1 = sharedPreferences.getString("name", "")
        val a = sharedPreferences.getInt("age", 0)

        // Setting the fetched data
        // in the EditTexts
        name?.setText(s1)
        age?.setText(a.toString())
    }

    override fun onPause() {
        super.onPause()

        // Creating a shared pref object
        // with a file name "MySharedPref"
        // in private mode

        val myEdit = sharedPreferences.edit()

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name?.text.toString())
        myEdit.putInt("age", age?.text.toString().toInt())
        myEdit.apply()
    }

}
