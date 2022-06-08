package com.example.hilt_example.binding

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductDataItem
import com.example.kotlinroomdbcrud.databinding.ProductlistItemBinding

class BindingAdapter {


    companion object {

        val TAG = "BindingAdapter"

        @BindingAdapter("setProductList")
        @JvmStatic // add this line !!
        fun setProductList(
            view: RecyclerView,
            arrayList: ArrayList<ProductDataItem>?
            ) {

            if(!arrayList.isNullOrEmpty()) {
                val adapter = object : GenericRecyclerViewAdapter<ProductDataItem, ProductlistItemBinding>(
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
                Log.d("BindingAdapter", "setProductList: is NULL")
            }
        }

        @BindingAdapter("setImageUrl")
        @JvmStatic
        fun ImageView.setImageUrl(url: String) {

            Glide.with(this.context).load(url).into(this)
        }

        @BindingAdapter("customTextColor")
        @JvmStatic
        fun customTextColor(textView: TextView, model: ProductDataItem) {

            Log.d(TAG, "customTextColor: ${model.title}")
            textView.text = model.title

            if(model.price > 50) {
                textView.setTextColor(textView.resources.getColor(R.color.purple_500))
            }
            else {
                textView.setTextColor(textView.resources.getColor(R.color.black))
            }
        }
    }
}