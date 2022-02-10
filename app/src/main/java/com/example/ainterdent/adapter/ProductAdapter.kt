package com.example.ainterdent.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ainterdent.R
import com.example.ainterdent.model.Producto
import com.squareup.picasso.Picasso

class ProductAdapter(
    private val products: List<Producto>,
    private val onClickProducto: (id : Int, photoView:View)-> Unit): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val producto =  products[position]
        Picasso.get().load(producto.imgUrl).into(holder.imgUrl)
        holder.precio.text = producto.precioUnit.toString()
        holder.stock.text = producto.stock.toString()
        holder.nameproduct.text=producto.nomPro

        holder.imgUrl.setOnClickListener {
            onClickProducto.invoke(producto.codPro, holder.imgUrl)
        }
    }

    override fun getItemCount() = products.size


    class ViewHolder( itemView : View) : RecyclerView.ViewHolder(itemView)  {
        val imgUrl : ImageView = itemView.findViewById(R.id.producimg)
        val precio : TextView = itemView.findViewById(R.id.producprecio)
        val stock : TextView = itemView.findViewById(R.id.tvstockproduc)
        val nameproduct : TextView = itemView.findViewById(R.id.producname)

    }

}