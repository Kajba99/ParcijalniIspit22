package com.example.parcijalniispit

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
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

    private val args by navArgs<MainFragmentArgs>()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully deleted: ${args.currentUser.naziv}" , Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setMessage("Are you sure you want to delete ${args.currentUser.detalj}?")
        builder.create().show()
    }



}