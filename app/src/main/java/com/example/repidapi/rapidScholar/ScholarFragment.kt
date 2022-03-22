package com.example.repidapi.rapidScholar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.repidapi.MyViewModel
import com.example.repidapi.RapidRetrofit
import com.example.repidapi.Service
import com.example.repidapi.databinding.ScholarFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScholarFragment : Fragment(){

    private var _binding: ScholarFragmentBinding? = null
    private val binding get() = _binding!!

    private val myRetrofit = RapidRetrofit()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ScholarFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        binding.progressBar.isVisible = true

        myViewModel.scholarLiveData.observe(viewLifecycleOwner){

            binding.progressBar.isVisible = false

            binding.ts.text = it.ts.toString()
            binding.total.text = it.total.toString()
            binding.deviceType.text = it.deviceType
            binding.deviceRegion.text = it.deviceRegion
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}