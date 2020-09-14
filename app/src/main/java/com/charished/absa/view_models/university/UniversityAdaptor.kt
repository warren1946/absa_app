package com.charished.absa.view_models.university

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charished.absa.R
import com.charished.absa.databinding.ItemUniversityBinding
import com.charished.absa.models.entities.University

class UniversityAdaptor (private val listener: UniversityItemListener, private val fromCountryFragment: Boolean) : RecyclerView.Adapter<UniversityViewHolder>(){

    interface UniversityItemListener {
        fun onClickedUniversity(university: University, view: View)
    }

    private val items = ArrayList<University>()

    fun setItems(items: ArrayList<University>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding: ItemUniversityBinding = ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding, listener, fromCountryFragment)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) = holder.bind(items[position])
}

class UniversityViewHolder(
    private val itemBinding: ItemUniversityBinding,
    private val listener: UniversityAdaptor.UniversityItemListener,
    private val fromCountryFragment: Boolean
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var university: University

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: University) {
        this.university = item
        itemBinding.tvName.text = item.name
        itemBinding.tvCountry.text = "${item.country} (${item.alpha_two_code})"
        itemBinding.tvDomain.text = item.domains.joinToString()

        // Change icon if the country fragment is using this adapter
        if(fromCountryFragment){
            itemBinding.imgMenu.setImageResource(R.drawable.ic_arrow_right)
        }
    }

    override fun onClick(view: View?) {
        listener.onClickedUniversity(university, itemBinding.imgMenu)
    }
}