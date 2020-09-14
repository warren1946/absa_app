package com.charished.absa.view_models.country

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.charished.absa.view_models.university.UniversityAdaptor
import com.charished.absa.databinding.FragmentCountryUniversityBinding
import com.charished.absa.models.entities.University
import com.charished.absa.utils.Resource
import com.charished.absa.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryUniversityFragment : Fragment(), UniversityAdaptor.UniversityItemListener {

    private var binding: FragmentCountryUniversityBinding by autoCleared()
    private val viewModel: CountryUniversityViewModel by viewModels()
    private lateinit var adapter: UniversityAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryUniversityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("country")?.let { viewModel.start(it) }
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = UniversityAdaptor(this, true)
        binding.rvUniversity.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUniversity.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.universities.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedUniversity(university: University, view: View) {
        if(university.web_pages.count() == 1){
            // go to website
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(university.web_pages.joinToString())
            startActivity(intent)
        }else{
            //TODO show list of websites to choose from
        }
    }
}