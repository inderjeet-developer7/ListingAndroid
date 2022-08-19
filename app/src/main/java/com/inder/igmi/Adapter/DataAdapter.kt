package com.inder.igmi.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inder.igmi.Model.Listed
import com.inder.igmi.R
import com.inder.igmi.databinding.ItemViewBinding


class DataAdapter(var context: Context, var mList: MutableList<Listed>) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        var data = mList.get(position)

        if (!data.image.isNullOrEmpty()){
            Glide.with(context).load(data.image).into(holder.binding.ivImage)
        }
        with(holder){
            binding.tvName.setText(data.business_name)
            binding.tvRating.setText(data.rating +"/5")
            binding.tvLocation.setText(data.address)
            binding.tvDescription.setText(data.description)

            var timeList = data.time_available
            val adapter = TimeAdapter(context, timeList)
            val layoutManager = GridLayoutManager(context, 4)
            binding.rvTimeRecyclerview.setLayoutManager(layoutManager)
            binding.rvTimeRecyclerview.setAdapter(adapter)


        }



    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemViewBinding.bind(itemView)



    }

}