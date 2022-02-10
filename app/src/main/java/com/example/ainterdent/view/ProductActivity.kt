package com.example.ainterdent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ainterdent.R
import com.example.ainterdent.adapter.ProductAdapter
import com.example.ainterdent.model.Producto
import com.example.ainterdent.viewmodel.ProductActivityViewModel
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    lateinit var viewModel: ProductActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        viewModel = ViewModelProvider(this).get(ProductActivityViewModel::class.java)
        viewModel.products.observe(this, Observer {
            loadRecyclerView(it)
        })
        viewModel.loadProducts()


        //search in real time
        searchTerm.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                viewModel.search(searchTerm.text.toString())
            }
        })
    }

    fun loadRecyclerView(products : List<Producto>){
        rec_products.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = ProductAdapter(products){extraId, photoView->
                val intent = Intent(this@ProductActivity ,ProductDetail::class.java)
                intent.putExtra("id", extraId)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as AppCompatActivity, photoView, "photoToAnimate")
                startActivity(intent, options.toBundle())
            }

        }
    }


}