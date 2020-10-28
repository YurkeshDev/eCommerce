package com.app.ecommerce.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.ecommerce.model.Product
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ProductDetailsViewModel : ViewModel() {

    val productDetails = MutableLiveData<Product>()

    fun fetchProductDetails(productTitle:String) {
        //coroutines
        viewModelScope.launch(Dispatchers.Default) {
            val json =
                URL("https://gist.githubusercontent.com/YurkeshDev/62fc13187533abc842ef51d00e6877ce/raw/9dc48e232dfaac64ad0dce74d9c7e3bf29f8b31e/shopping_products.json").readText()
            val list = Gson ().fromJson(json, Array<Product>::class.java).toList()
            val product = list.first { it.title == productTitle }

            productDetails.postValue(product)

        }
    }
}