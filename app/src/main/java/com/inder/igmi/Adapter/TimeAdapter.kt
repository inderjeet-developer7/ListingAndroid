package com.inder.igmi.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.inder.igmi.Model.Listed
import com.inder.igmi.Model.TimeAvailable
import com.inder.igmi.R
import com.inder.igmi.databinding.ItemTimeSlotBinding

class TimeAdapter(var context: Context, var mList: List<TimeAvailable>) :
    RecyclerView.Adapter<TimeAdapter.ViewHolder>() {
    //    var row_index = 0
    var isSelected: Boolean? = null
    var row_index:Int?=null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_time_slot, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: TimeAdapter.ViewHolder, position: Int) {

        var time = mList.get(position).time
        with(holder){
            binding.tvTime.setText(time)
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemTimeSlotBinding.bind(itemView)



    }

}