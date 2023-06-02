package com.example.mycontactsapp.ui.update

import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.example.mycontactsapp.R
import com.example.mycontactsapp.data.models.ContactsData
import com.example.mycontactsapp.databinding.FragmentUpdateBinding
import com.example.mycontactsapp.ui.SharedViewModel
import com.example.mycontactsapp.ui.main.ContactsViewModel

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mContactsViewModel: ContactsViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        binding.currentNameEt.setText(args.currentItem.name)
        binding.currentNoEt.setText(args.currentItem.no)
        binding.currentAddressEt.setText(args.currentItem.address)
        binding.currentPrioritiesSpinner.setSelection(mSharedViewModel.parsePriority(args.currentItem.groups))
        binding.currentPrioritiesSpinner.onItemSelectedListener = mSharedViewModel.listener
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> updateItem()
            R.id.menu_delete -> confirmItemRemovel()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        val name = binding.currentNameEt.text.toString()
        val no = binding.currentNoEt.text.toString()
        val address = binding.currentAddressEt.text.toString()
        val priority = binding.currentPrioritiesSpinner.selectedItem.toString()

        val validation = mSharedViewModel.verifyDataFromUser(name,no,address)
        if (validation) {
            val updateItem = ContactsData(
                args.currentItem.id,
                name,
                no,
                mSharedViewModel.parsePriority(priority),
                address
            )
            mContactsViewModel.updateData(updateItem)
            Toast.makeText(requireContext(), "Successfully Updated!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun confirmItemRemovel() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mContactsViewModel.deleteItem(args.currentItem)
            Toast.makeText(
                requireContext(),
                "Succesfully Removed: ${args.currentItem.name}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete '${args.currentItem.name}'?")
        builder.setMessage("Are you sure you want to delete '${args.currentItem.name}'?")
        builder.create().show()
    }
}