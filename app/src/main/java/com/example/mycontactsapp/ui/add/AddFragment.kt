package com.example.mycontactsapp.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mycontactsapp.R
import com.example.mycontactsapp.data.models.ContactsData
import com.example.mycontactsapp.databinding.FragmentAddBinding
import com.example.mycontactsapp.ui.SharedViewModel
import com.example.mycontactsapp.ui.main.ContactsViewModel

class AddFragment: Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val contactsViewModel: ContactsViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        binding.prioritiesSpinner.onItemSelectedListener = sharedViewModel.listener
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add) {
            insertDataToDb()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mName = binding.nameEt.text.toString()
        val mNo = binding.noEt.text.toString()
        val mPriority = binding.prioritiesSpinner.selectedItem.toString()
        val mAddress = binding.addressEt.text.toString()

        val validation = sharedViewModel.verifyDataFromUser(mName,mNo, mAddress)
        if (validation) {
            val newData = ContactsData(
                0,
                mName,
                mNo,
                sharedViewModel.parsePriority(mPriority),
                mAddress
            )
            contactsViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }
}