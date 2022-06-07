package com.example.kotlinroomdbcrud.dataBindingExample

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductDataItem
import com.example.kotlinroomdbcrud.dataBindingExample.recyclerView.GenericRecyclerViewAdapter
import com.example.kotlinroomdbcrud.databinding.ProductlistItemBinding

class BindingAdapter {

    companion object {

        @BindingAdapter("setProductList")
        @JvmStatic // add this line !!
        fun setProductList(
            view: RecyclerView,
            arrayList: ArrayList<ProductDataItem>?
            ) {

            if(!arrayList.isNullOrEmpty()) {
                val adapter = object : GenericRecyclerViewAdapter<ProductDataItem,ProductlistItemBinding>(
                    view.context,
                    arrayList
                ) {
                    override val layoutResId: Int
                        get() = R.layout.productlist_item

                    override fun onBindData(
                        model: ProductDataItem,
                        position: Int,
                        dataBinding: ProductlistItemBinding
                    ) {
                        dataBinding.adapterModel = model
                        dataBinding.executePendingBindings()
                    }

                    override fun onItemClick(model: ProductDataItem, position: Int) {

                    }

                }

                view.adapter = adapter
            }
            else {
                Log.d("BindingAdpater", "setProductList: is NULL")
            }
        }

        @BindingAdapter("setImageUrl")
        @JvmStatic // add this line !!
        fun ImageView.setImageUrl(url: String) {

            Glide.with(this.context).load(url).into(this)
        }

    }
}