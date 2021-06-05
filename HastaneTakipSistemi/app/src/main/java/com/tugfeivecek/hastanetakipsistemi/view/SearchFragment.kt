package com.tugfeivecek.hastanetakipsistemi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugfeivecek.hastanetakipsistemi.adapter.SearchAdapter
import com.tugfeivecek.hastanetakipsistemi.databinding.FragmentSearchBinding
import com.tugfeivecek.hastanetakipsistemi.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val adapterSearch = SearchAdapter(arrayListOf())
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.editTextSearch.isIconifiedByDefault = true
        binding.editTextSearch.isFocusable = true
        binding.editTextSearch.isIconified = false
        binding.editTextSearch.requestFocusFromTouch()

        binding.editTextSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                binding.constraintLayoutTitles.visibility = View.INVISIBLE
                binding.constraintLayoutResults.visibility = View.VISIBLE
                searchViewModel.getSearchDataFromAPI(newText!!)
                binding.rvSearch.layoutManager = LinearLayoutManager(context)
                binding.rvSearch.adapter = adapterSearch

                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.constraintLayoutTitles.visibility = View.INVISIBLE
                binding.constraintLayoutResults.visibility = View.VISIBLE
                searchViewModel.getSearchDataFromAPI(query!!)
                binding.rvSearch.layoutManager = LinearLayoutManager(context)
                binding.rvSearch.adapter = adapterSearch

                return false
            }
        })
        observerLiveQueryData()
        binding.swipeRefreshLayoutSearch.setOnRefreshListener {
            binding.swipeRefreshLayoutSearch.isRefreshing = false
        }

        binding.rvSearch.layoutManager = LinearLayoutManager(context)
        binding.rvSearch.adapter = adapterSearch
    }

    private fun observerLiveQueryData() {
        searchViewModel.searches.observe(viewLifecycleOwner, Observer { items ->
            items?.let {
                binding.rvSearch.visibility = View.VISIBLE
                adapterSearch.updateSearchList(items)


            }
        })
    }
}