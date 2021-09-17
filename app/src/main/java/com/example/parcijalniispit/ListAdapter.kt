package com.example.parcijalniispit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcijalniispit.database.ViewModel
import com.example.parcijalniispit.model.User
import kotlinx.android.synthetic.main.podsjetnik.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {



    private var userList = emptyList<User>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.podsjetnik, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.textViewNaziv.text = currentItem.naziv
        holder.itemView.textViewDetalj.text = currentItem.detalj


        holder.itemView.rowLayout.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}