package com.example.repidapi.rapidSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repidapi.RapidRetrofit
import com.example.repidapi.MyViewModel
import com.example.repidapi.Service
import com.example.repidapi.databinding.SearchFragmentBinding
import com.example.repidapi.resultat.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapterRapidSearch: AdapterSearch
    private val myRetrofit = RapidRetrofit()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)

        adapterRapidSearch = AdapterSearch()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterRapidSearch

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val serviceSearchApi = myRetrofit.retrofitRapid.create(Service::class.java)
        val myViewModel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        myViewModel.queryLivedata.observe(viewLifecycleOwner) {

            binding.progressBar.isVisible = true

            val res = serviceSearchApi.rapidSearch(it)
            res.enqueue(object : Callback<SearchResult> {
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    val result = response.body() as SearchResult
                    if (response.isSuccessful) {
                        adapterRapidSearch.setAll(result.results)
                    } else {
                        Toast.makeText(requireContext(), "no Successful", Toast.LENGTH_LONG).show()
                    }
                    binding.progressBar.isVisible = false
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Toast.makeText(requireContext(), t.toString(), Toast.LENGTH_LONG).show()
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