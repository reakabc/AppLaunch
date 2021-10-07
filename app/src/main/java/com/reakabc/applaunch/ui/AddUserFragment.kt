package com.reakabc.applaunch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.reakabc.applaunch.R
import com.reakabc.applaunch.databinding.FragmentAddUserBinding
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.applaunch.viewmodels.AddUserViewModel
import com.reakabc.applaunch.viewmodels.AddUserViewModelFactory

class AddUserFragment : Fragment() {

    lateinit var viewModel: AddUserViewModel
    lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        userRepository = UserRepository(UserDatabase.getDatabase(requireContext()), requireContext())
        viewModel = ViewModelProvider(this, AddUserViewModelFactory(userRepository)).get(
            AddUserViewModel::class.java)
        viewModel.addStatus.observe(viewLifecycleOwner, {
            if (it.equals("added")){
                findNavController().popBackStack()
            }else{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

        val binding: FragmentAddUserBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_user, container, false
        )

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnAdd.setOnClickListener {
            viewModel.addUser(User(null, binding.etFirstName.text.toString(), binding.etLastName.text.toString(), binding.etEmail.text.toString()))
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // binding.setVariableName(data)
        return binding.root
    }

}