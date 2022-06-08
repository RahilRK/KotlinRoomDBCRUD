
package com.example.kotlinroomdbcrud.dataBindingExample.recyclerView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinroomdbcrud.dataBindingExample.model.ProductData
import com.example.kotlinroomdbcrud.dataBindingExample.network.ApiClient
import com.example.kotlinroomdbcrud.dataBindingExample.network.ResponseHandler
import com.example.kotlinroomdbcrud.util.ViewModelBase
import kotlinx.coroutines.launch

class ProductListViewModel: ViewModelBase() {

    var responseLiveProductList = MutableLiveData<ResponseHandler<ProductData?>>()
    var productListRepository = ProductListRepository(ApiClient.getApiInterface())

    init {
        callApi()
    }

    private fun callApi() {
        viewModelScope.launch {
            responseLiveProductList.postValue(ResponseHandler.Loading)
            responseLiveProductList.value = productListRepository.getProductList()
        }
    }

}