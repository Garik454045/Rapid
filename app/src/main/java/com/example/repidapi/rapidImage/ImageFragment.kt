package com.example.repidapi.rapidImage

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.repidapi.RapidRetrofit
import com.example.repidapi.MyViewModel
import com.example.repidapi.Service
import com.example.repidapi.data.SearchQueryRequestBody
import com.example.repidapi.databinding.ImageFragmentBinding
import com.example.repidapi.resultat.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageFragment : Fragment() {

    private var _binding: ImageFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterRapidImage: AdapterImage
    private val myRetrofit = RapidRetrofit()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ImageFragmentBinding.inflate(inflater, container, false)

        adapterRapidImage = AdapterImage()
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerView.adapter = adapterRapidImage

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val serviceImageApi = myRetrofit.retrofitRapid.create(Service::class.java)
        val myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        myViewModel.queryLivedata.observe(viewLifecycleOwner) {

            binding.progressBar.isVisible = true

            val result = serviceImageApi.rapidImage(it)
            result.enqueue(object : Callback<SearchResult> {
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    val res = response.body() as SearchResult

                    if (response.isSuccessful) {
                        val urls = mutableListOf<String>()
                        val list = res.imageResults
                        list.forEach {
                            urls.add(it.image.src)
                        }
                        adapterRapidImage.setAll(urls)
                    } else {
                        Toast.makeText(requireContext(), "inch vor ban ayn che", Toast.LENGTH_SHORT)
                            .show()
                    }
                    binding.progressBar.isVisible = false
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Toast.makeText(requireContext(), "banm en che", Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }

            })

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}