package com.pw.pwrickapp.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pw.pwrickapp.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Ritik on: 31/08/24
 */

/**
 * The base fragment class for all fragments.
 */
@AndroidEntryPoint
open class BaseFragment : Fragment() {
    /**
     * Holds instance of user view model.
     */
    val characterViewModel: CharacterViewModel by viewModels()

    /**
     * Shows toast message.
     */
    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}