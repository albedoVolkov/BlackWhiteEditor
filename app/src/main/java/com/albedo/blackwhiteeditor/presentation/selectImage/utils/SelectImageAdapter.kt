package com.albedo.blackwhiteeditor.presentation.selectImage.utils

import android.annotation.SuppressLint
import android.icu.text.DateFormat.getDateInstance
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albedo.blackwhiteeditor.databinding.ItemRslActivitySelectImageBinding
import com.albedo.blackwhiteeditor.domain.models.LayoutImageUIState

class SelectImageAdapter : RecyclerView.Adapter<SelectImageAdapter.ItemViewHolder>() {

    companion object{
        const val TAG = "SelectImageAdapter"
    }


    private var data : List<LayoutImageUIState> = listOf()

    lateinit var onClickListenerMain: OnClickListener
    lateinit var onClickListenerDelete: OnClickListener

    inner class ItemViewHolder(private var binding: ItemRslActivitySelectImageBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemModel: LayoutImageUIState) {
            binding.main.setOnClickListener {
                onClickListenerMain.onClick(itemModel)
            }

            binding.btnDeleteItem.setOnClickListener {
                onClickListenerDelete.onClick(itemModel)
            }

            binding.txtNameInfo.text = itemModel.name
//            getDateInstance().format(itemModel.date)
//            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val date = getDateInstance().format(itemModel.date)
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