package com.app.ecommerce

import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.products_details.*

class ProductDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.products_details)

        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photo_url")

        productName.text = title
        Picasso.get().load(photoUrl).into(photo)

        availability.setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("$title, Is in stock")
                .setPositiveButton("ok"
                ) { dialog, which -> }
                .create()
                .show()
        }
    }
}