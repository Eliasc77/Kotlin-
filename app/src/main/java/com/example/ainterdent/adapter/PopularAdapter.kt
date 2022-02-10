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
import kotlinx.android.synthetic.main.popular_item.view.*

class PopularAdapter (
    private val products: List<Producto>,
    private val onClickProducto: (id : Int, photoView:View)-> Unit) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(product.imgUrl).into(holder.imgUrl)
        holder.precio.text = product.precioUnit.toString()
        holder.stock.text = product.stock.toString()
        holder.description.text = product.nomPro

        //when client click on product, do this action
        holder.imgUrl.setOnClickListener{
            onClickProducto.invoke(product.codPro, holder.imgUrl)
        }
    }

    override fun getItemCount() = products.size


    class ViewHolder ( itemView : View) : RecyclerView.ViewHolder(itemView) {
        val imgUrl : ImageView = itemView.findViewById(R.id.popularImg)
        val precio : TextView = itemView.findViewById(R.id.etPrecioPopular)
        val stock : TextView = itemView.findViewById(R.id.erStockPopular)
        val description : TextView = itemView.findViewById(R.id.tvPopularName)


    }
}