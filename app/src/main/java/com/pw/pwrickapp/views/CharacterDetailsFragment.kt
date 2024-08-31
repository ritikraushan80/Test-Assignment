package com.pw.pwrickapp.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.pw.pwrickapp.R
import com.pw.pwrickapp.base.BaseFragment
import com.pw.pwrickapp.databinding.FragmentCharacterDetailsBinding
import com.pw.pwrickapp.helpers.StringConstants
import com.pw.pwrickapp.model.NetworkState
import com.pw.pwrickapp.model.Results
import com.pw.pwrickapp.utils.CallbackListener
import com.pw.pwrickapp.utils.SharedPref
import com.pw.pwrickapp.views.adapter.EpisodeAdapter

/**
 * Created by Ritik on: 31/08/24
 */

class CharacterDetailsFragment : BaseFragment() {
    private lateinit var _binding:FragmentCharacterDetailsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backBtn.setOnClickListener {
            requireView().findNavController().navigateUp()
        }

        /**
         * Observer and request to call API
         */
        val characterId =  SharedPref.read(StringConstants.CHAR_ID, null).toString().toInt()
        characterViewModel.getCharacterDetails(characterId)
        characterViewModel.characterDetails.observe(viewLifecycleOwner, ::onGettingCharacterDetails)

    }
    /**
     * Function handle api result of character details
     */
    private fun onGettingCharacterDetails(state: NetworkState<Results>?) {
        if (state is NetworkState.Loading) {
            binding.loader.visibility = View.VISIBLE
            binding.scrollview.visibility = View.GONE
            return
        }
        when (state) {
            is NetworkState.Success -> {
                binding.loader.visibility = View.GONE
                binding.scrollview.visibility = View.VISIBLE

                state.data.let {
                    binding.nameTextView.text = it?.name
                    binding.statusTextView.text = it?.status
                    binding.speciesTextView.text = it?.species
                    binding.genderTextView.text = it?.gender
                    binding.locationTextView.text = it?.location?.name

                    binding.characterImageView.load(it?.image) {
                        crossfade(true)
                        placeholder(R.drawable.logo)
                    }
                }
                state.data?.episode.let {
                    if (it != null) {
                        episodeList(it)
                    }
                }
            }
            is NetworkState.Error -> {
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
     * Function to Episode list
     */
    private fun episodeList(data:ArrayList<String>){
        val layoutManager = LinearLayoutManager(requireContext())
        binding.episodeRecyclerview.layoutManager = layoutManager
        val adapter = EpisodeAdapter(requireContext(), data)
        binding.episodeRecyclerview.adapter = adapter
    }
}