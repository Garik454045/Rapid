package com.example.repidapi

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.repidapi.databinding.ActivityMainBinding
import com.example.repidapi.rapidImage.ImageFragment
import com.example.repidapi.rapidScholar.ScholarFragment
import com.example.repidapi.rapidSearch.SearchFragment
import com.example.repidapi.rapidSerp.SerpFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val searchFragment = SearchFragment()
    private val imageFragment = ImageFragment()
    private val scholarFragment = ScholarFragment()
    private val serpFragment = SerpFragment()

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        commitDefFragment()

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        binding.btnSearch.setOnClickListener {
            myViewModel.setValue(binding.editText.text.toString())
        }

        binding.btnRapidSearch.setOnClickListener {
            commitDefFragment()
        }

        binding.btnRapidImages.setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, imageFragment)
                .commit()
        }

        binding.btnRapidScholar.setOnClickListener {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout,scholarFragment)
                .commit()
        }

        binding.btnRapidSerp.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout,serpFragment)
                .commit()
        }
    }


    private fun commitDefFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, searchFragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}