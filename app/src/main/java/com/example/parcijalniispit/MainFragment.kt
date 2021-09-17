package com.example.parcijalniispit

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcijalniispit.database.ViewModel
import com.example.parcijalniispit.model.User
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {

    private lateinit var mUserViewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)


        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        view.button.setOnClickListener {
            insertDataToDatabase()
        }

        // recyclerView
        val adapter = ListAdapter()
        val recyclerview = view.recyclerview
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // observam info
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)
        })



        return view
    }

    private fun insertDataToDatabase() {
        val naziv = naziv.text.toString()
        val detalj = detalji.text.toString()

        if (inputCheck(naziv, detalj)) {

            val user = User(0, naziv,detalj )

            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!" , Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(requireContext(), "Please fill out all fields!" , Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(naziv: String, detalj: String): Boolean {
        return !(TextUtils.isEmpty(naziv) && TextUtils.isEmpty(detalj))

    }




}