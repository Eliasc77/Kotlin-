package com.example.ainterdent.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ainterdent.R
import com.example.ainterdent.adapter.ProductAdapter
import com.example.ainterdent.model.Producto
import com.example.ainterdent.viewmodel.CategoryActivityViewModel
import kotlinx.android.synthetic.main.activity_categorias.*
import kotlinx.android.synthetic.main.activity_product.*

class CategoriasActivity : AppCompatActivity() {

    lateinit var viewModel : CategoryActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorias)

        viewModel = ViewModelProvider(this).get(CategoryActivityViewModel::class.java)
        var categoriaId = intent.getStringExtra("categoriaId")

        viewModel.products.observe(this, Observer {
            loadRecyclerView(it)
        })
        viewModel.listProductsByCategory(categoriaId.toString())
    }

    fun loadRecyclerView(products : List<Producto>){
        rec_products_category.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = ProductAdapter(products){extraId, photoView->
                val intent = Intent(this@CategoriasActivity, ProductDetail::class.java)
                intent.putExtra("id", extraId)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as AppCompatActivity, photoView, "photoToAnimate")
                startActivity(intent, options.toBundle())
            }

        }
    }
}