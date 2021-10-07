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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reakabc.applaunch.R
import com.reakabc.applaunch.adapters.UserAdapter
import com.reakabc.applaunch.databinding.FragmentHomeBinding
import com.reakabc.applaunch.db.UserDatabase
import com.reakabc.applaunch.models.User
import com.reakabc.applaunch.repository.UserRepository
import com.reakabc.applaunch.utils.SwipeToDeleteCallback
import com.reakabc.examapp.viewmodels.HomeViewModel
import com.reakabc.applaunch.viewmodels.HomeViewModelFactory

class HomeFragment : Fragment() {

    lateinit var viewModel: HomeViewModel
    lateinit var userRepository: UserRepository

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: UserAdapter
    var list:ArrayList<User> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        userRepository = UserRepository(UserDatabase.getDatabase(requireContext()), requireContext())
        viewModel = ViewModelProvider(this, HomeViewModelFactory(userRepository)).get(
            HomeViewModel::class.java)

        viewModel.getUserList()
        viewModel.list.observe(viewLifecycleOwner, {
            list = it as ArrayList<User>
//            Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_SHORT).show()
            initializeRecyclerView(list)
        })

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        binding.btnAddUser.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addUserFragment)
        }

        recyclerView = binding.recyclerViewUserList


        return binding.root
    }

    private fun initializeRecyclerView(list:List<User>) {

        adapter = UserAdapter(list, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        val swipeToDeleteCallback = object : SwipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition

                viewModel.deleteUser(list[pos])  //delete from database
                adapter.notifyItemRemoved(pos)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }


}