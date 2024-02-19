package com.albedo.blackwhiteeditor.presentation.selectImage

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albedo.blackwhiteeditor.databinding.ItemRslActivitySelectImageBinding
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState
import java.text.SimpleDateFormat

class SelectImageAdapter (private val context: Context) : RecyclerView.Adapter<SelectImageAdapter.ItemViewHolder>() {

    val TAG = "SelectImageAdapter"

    private var data : List<LayoutImageUIState> = listOf()

    lateinit var onClickListener: OnClickListener

    inner class ItemViewHolder(private var binding: ItemRslActivitySelectImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemModel: LayoutImageUIState) {
            binding.main.setOnClickListener {
                onClickListener.onClick(itemModel)
            }
            binding.txtNameInfo.text = itemModel.name

            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val date = sdf.format(itemModel.date)
            binding.txtDateInfo.text = date

//            if (itemModel.image.isNotEmpty()) {
//                Glide.with(context)
//                .load(itemModel.image)
//                .error(R.drawable.not_loaded_one_image)
//                .placeholder(R.drawable.not_loaded_one_image)
//                .into(binding.imageViewCategory)
//        }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemRslActivitySelectImageBinding.inflate(itemView, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemId(position: Int) = data[position].id.toLong()

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList : List<LayoutImageUIState>){
        Log.d(TAG, "newList $newList")
        data = newList
        notifyDataSetChanged()
    }

    interface OnClickListener {
        fun onClick(itemData: LayoutImageUIState)
    }
}