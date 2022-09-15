package com.example.myapplicationdd

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Recycler_View(private  val datalist:List<Mydata>):RecyclerView.Adapter<Recycler_View.MyHolder>()

{
    private  lateinit var context:Context
    inner class MyHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idschool: TextView = view.findViewById(R.id.school_id)
        var orgnisation=view.findViewById<TextView>(R.id.organisation_name)
        var email=view.findViewById<TextView>(R.id.email)
        var phone=view.findViewById<TextView>(R.id.phone)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context=parent.context
      return  MyHolder(LayoutInflater.from(context).inflate(R.layout.list_item2,parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       val data=datalist[position]
        holder.idschool.text= data.School_Id.toString()
       holder.orgnisation.text=data.Org_Name.toString()
        holder.email.text=data.Email.toString()
        holder.phone.text=data.Telephone.toString()




    }

    override fun getItemCount(): Int =datalist.size

}