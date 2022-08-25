package com.mytest.example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mytest.example.data.model.Data
import com.mytest.example.data.util.Resource
import com.mytest.example.databinding.ActivityMainBinding
import com.mytest.example.presentation.adapter.ExpandableCovidListAdapter
import com.mytest.example.presentation.viewmodel.CovidViewModel
import com.mytest.example.presentation.viewmodel.CovidViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: CovidViewModelFactory
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CovidViewModel
    var expandableDetailList = HashMap<String, List<Data>>()

     var titleList: List<String>? = null
    private var adapter: ExpandableCovidListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)
            .get(CovidViewModel::class.java)

        getCovidData()
    }

    private fun getCovidData() {
        viewModel.getCovidData()
        viewModel.covidData.observe(this, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let {
                        Log.e("Success", "****" + it.data.get(0))
                        binding.progressBar.visibility = View.GONE
                        expandableDetailList.put("Covid Data List",it.data)
                        titleList = ArrayList(expandableDetailList.keys)
                        initRecyclerView(titleList as ArrayList<String>,expandableDetailList)
                    }
                }
                is Resource.Error -> {
                    Log.e("Error", "****")
                    binding.progressBar.visibility = View.GONE
                    response.message?.let {
                        Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    Log.e("Loading", "****")
                    binding.progressBar.visibility = View.VISIBLE

                }
            }

        })
    }

    private fun initRecyclerView(
        titleList: ArrayList<String>,
        expandableDetailList: HashMap<String, List<Data>>
    ) {
        val expandableListView = binding.expandableListView
        adapter = ExpandableCovidListAdapter(this, titleList as ArrayList<String>, expandableDetailList)
        expandableListView.setAdapter(adapter)

        expandableListView.setOnGroupExpandListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                (titleList as ArrayList<String>)[groupPosition] + " List Expanded.",
                Toast.LENGTH_SHORT
            ).show()
        }

        expandableListView.setOnGroupCollapseListener { groupPosition ->
            Toast.makeText(
                applicationContext,
                (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.",
                Toast.LENGTH_SHORT
            ).show()
        }

        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(
                applicationContext,
                "Clicked: " + (titleList as ArrayList<String>)[groupPosition] + " -> " + expandableDetailList[(titleList as ArrayList<String>)[groupPosition]]!!.get(
                    childPosition
                ),
                Toast.LENGTH_SHORT
            ).show()
            false
        }

    }
}