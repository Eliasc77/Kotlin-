package com.example.ainterdent.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ainterdent.R
import com.example.ainterdent.viewmodel.DetailProductActivityViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.*
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetail : AppCompatActivity() {

    lateinit var viewModel : DetailProductActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        viewModel = ViewModelProvider(this).get(DetailProductActivityViewModel::class.java)
        val id = intent.getIntExtra("id", -1)

        viewModel.productDetails.observe(this , Observer {
            detailed_name.text= it.nomPro
            detailed_desc.text= it.description
            detailed_price.text = it.precioUnit.toString()
            Picasso.get().load(it.imgUrl).into(detailed_img)
            det_stock.text = it.stock.toString()

            if(it.isOnSale){
                isOnSale.setTextColor(Color.parseColor("#03ac13"))
                isOnSale.setText("Hay Stock")



            }else{
                isOnSale.setTextColor(Color.parseColor("#FF0000"))
                isOnSale.setText("No Hay Stock")

            }
        })
        viewModel.fetchProductDetails(id)
    }
}