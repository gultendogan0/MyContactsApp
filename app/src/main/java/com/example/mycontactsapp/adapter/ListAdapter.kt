package com.example.mycontactsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontactsapp.R
import com.example.mycontactsapp.data.models.ContactsData
import com.example.mycontactsapp.data.models.Groups
import com.example.mycontactsapp.databinding.RowLayoutBinding
import com.example.mycontactsapp.ui.family.FamilyFragment
import com.example.mycontactsapp.ui.family.FamilyFragmentDirections
import com.example.mycontactsapp.ui.friends.FriendsFragment
import com.example.mycontactsapp.ui.friends.FriendsFragmentDirections
import com.example.mycontactsapp.ui.list.ListFragment
import com.example.mycontactsapp.ui.list.ListFragmentDirections
import com.example.mycontactsapp.ui.other.OtherFragment
import com.example.mycontactsapp.ui.other.OtherFragmentDirections
import com.example.mycontactsapp.ui.work.WorkFragment
import com.example.mycontactsapp.ui.work.WorkFragmentDirections

class ListAdapter(private val fragment: Fragment) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ContactsData>()
    private var destinationFragment: Int = R.id.updateFragment

    fun setDestinationFragment(destination: Int) {
        destinationFragment = destination
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    inner class MyViewHolder(private val binding: RowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContactsData) {
            binding.nameTxt.text = item.name
            binding.noTxt.text = item.no
            binding.addressTxt.text = item.address

            when (item.groups) {
                Groups.FAMILY -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.priorityIndicator.context,
                        R.color.green
                    )
                )
                Groups.FRIENDS -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.priorityIndicator.context,
                        R.color.yellow
                    )
                )
                Groups.WORK -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.priorityIndicator.context,
                        R.color.red
                    )
                )
                Groups.OTHER -> binding.priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.priorityIndicator.context,
                        R.color.darkGray
                    )
                )
            }

            binding.rowBackground.setOnClickListener {
                val action = when (fragment) {
                    is ListFragment -> ListFragmentDirections.actionListFragmentToUpdateFragment(item)
                    is FamilyFragment -> FamilyFragmentDirections.actionNavGalleryToUpdateFragment(item)
                    is FriendsFragment -> FriendsFragmentDirections.actionNavSlideshowToUpdateFragment(item)
                    is WorkFragment -> WorkFragmentDirections.actionWorkFragmentToUpdateFragment(item)
                    is OtherFragment -> OtherFragmentDirections.actionOtherFragmentToUpdateFragment(item)
                    else -> null
                }

                action?.let {
                    binding.rowBackground.findNavController().navigate(it)
                }
            }
        }
    }

    fun setData(contactsData: List<ContactsData>) {
        val contactsDiffUtil = ContactsDiffUtil(dataList, contactsData)
        val contactsDiffResult = DiffUtil.calculateDiff(contactsDiffUtil)
        this.dataList = contactsData
        contactsDiffResult.dispatchUpdatesTo(this)
    }
}