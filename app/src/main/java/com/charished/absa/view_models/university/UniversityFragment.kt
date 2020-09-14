package com.charished.absa.view_models.university

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.charished.absa.R
import com.charished.absa.databinding.FragmentUniversityBinding
import com.charished.absa.models.entities.University
import com.charished.absa.utils.Resource
import com.charished.absa.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UniversityFragment : Fragment(), UniversityAdaptor.UniversityItemListener {

    private var binding: FragmentUniversityBinding by autoCleared()
    private val viewModel: UniversityViewModel by viewModels()
    private lateinit var adapter: UniversityAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUniversityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = UniversityAdaptor(this, false)
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
        // Create university
        val popupMenu: PopupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

        // Update second menu item based on selected universities country
        popupMenu.menu.getItem(1).setTitle("View All From ${university.country} (${university.alpha_two_code})")

        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.action_view_website ->
                    // Go to the website
                    visitWebsite(university)
                R.id.action_view_country ->
                    // Navigate to view more universities
                    findNavController().navigate(
                        R.id.action_universityFragment_to_countryUniversityFragment,
                        bundleOf("country" to university.country)
                    )
            }
            true
        })
        popupMenu.show()
    }

    fun visitWebsite(university: University) {

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