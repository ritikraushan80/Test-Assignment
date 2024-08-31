package com.pw.pwrickapp.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pw.pwrickapp.base.BaseFragment
import com.pw.pwrickapp.databinding.FragmentCharacterListBinding
import com.pw.pwrickapp.helpers.StringConstants
import com.pw.pwrickapp.model.NetworkState
import com.pw.pwrickapp.model.Results
import com.pw.pwrickapp.utils.CallbackListener
import com.pw.pwrickapp.utils.SharedPref
import com.pw.pwrickapp.utils.ThemeChangeListener
import com.pw.pwrickapp.views.adapter.CharacterAdapter

/**
 * Created by Ritik on: 31/08/24
 */

class CharacterListFragment : BaseFragment(), CallbackListener {
    private lateinit var _binding: FragmentCharacterListBinding
    private val binding get() = _binding
    private lateinit var adapter: CharacterAdapter
    private lateinit var themeChangeListener: ThemeChangeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ThemeChangeListener) {
            themeChangeListener = context
        } else {
            throw RuntimeException("$context must implement ThemeChangeListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Theme Click Listener
         */
        val themeMode = SharedPref.read(StringConstants.THEME_MODE, null)


        when (themeMode) {
            "light" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
            "dark" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            }
            else -> {
                // If the theme mode is not set or invalid, use the system default
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->
            if (!isChecked) {
                SharedPref.write(StringConstants.THEME_MODE, "light")
                themeChangeListener.onThemeChange(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                SharedPref.write(StringConstants.THEME_MODE, "dark")
                themeChangeListener.onThemeChange(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        /**
         * Observer and request to call API
         */
        characterViewModel.getCharacterList()
        characterViewModel.characters.observe(viewLifecycleOwner, ::onGettingCharactersData)
    }

    /**
     * Function handle api result of characterList
     */
    private fun onGettingCharactersData(state: NetworkState<ArrayList<Results>>?) {
        if (state is NetworkState.Loading) {
            binding.loader.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
            return
        }
        when (state) {
            is NetworkState.Success -> {
                binding.loader.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE

                state.data?.let { characterList(it) }
            }
            is NetworkState.Error -> {
                Log.e("Error", state.message.toString() + state.errCode)
                state.message?.let { showToast(it) }
            }
            is NetworkState.Failure -> {
                showToast(StringConstants.CONNECTION_ERROR)
            }
            else -> {
                showToast("Error occurred ")
            }
        }
    }

    /**
     * Function to list of character
     */
    private fun characterList(data: ArrayList<Results>) {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        adapter = CharacterAdapter(data, this)
        binding.recyclerView.adapter = adapter
    }

    /**
     * Click Listener of character item
     */
    override fun clickOnCharacterItem(id: Int) {
        SharedPref.write(StringConstants.CHAR_ID, id.toString())

        if (isAdded && activity != null) {
            requireView().findNavController()
                .navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment())
        }
    }

}