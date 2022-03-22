package com.example.repidapi.rapidSerp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.repidapi.MyViewModel
import com.example.repidapi.databinding.SerpFragmentBinding

class SerpFragment: Fragment() {

    private var _binding: SerpFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SerpFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        binding.progressBar.isVisible = true


        myViewModel.serpLiveDataResponse.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = false

            it?.run {
                binding.position.text = this.position.toString()
                binding.searchedResults.text = this.searchedResults.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}