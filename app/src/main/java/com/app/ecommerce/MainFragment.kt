package com.app.ecommerce

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.app.ecommerce.database.AppDatabase
import com.app.ecommerce.database.ProductsFromDataBase
import com.app.ecommerce.model.Product
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main,container,false)
//
//        doAsync {
//            val json = URL("https://finepointmobile.com/data/products.json").readText()
//
//            uiThread {
//                d("yurii","json:$json")
//                val product = Gson().fromJson(json,Array<Product>::class.java).toList()
//
//                root.recycler_View.apply {
//                    layoutManager = GridLayoutManager(activity,2)
//                    adapter = ProductsAdapter(product)
//                    root.progressBar.visibility = View.GONE
//
//                }
//            }
//        }

        doAsync {

            val db = databaseBuilder(
                activity!!.applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()

            val productsFromDataBase = db.productDao().getAll()

            val products = productsFromDataBase.map {

                Product (

                    it.title,
                    "https://finepointmobile.com/data/jeans1.jpg",
                    it.price,
                    true
                )
            }

            uiThread {
                root.recycler_View.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter( products)
                    root.progressBar.visibility = View.GONE
                }
            }
        }

//        root.progressBar.visibility = View.GONE

        val categories = listOf("Jeans", "Socks", "Suits", "Skirts", "Dresses","Yurii:)","Jeans", "Socks", "Suits", "Skirts", "Dresses","Yurii:)")

        root.categoriesRecycler.apply {
            layoutManager = LinearLayoutManager(activity,RecyclerView.HORIZONTAL,false)
            adapter = CategoriesAdapter(categories)
        }

        return root
    }
}