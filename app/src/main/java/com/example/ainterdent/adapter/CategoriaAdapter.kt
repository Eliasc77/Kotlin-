package com.example.ainterdent.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ainterdent.R
import com.example.ainterdent.model.Categoria
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.category_item.view.*

class CategoriaAdapter (
    private val categories: List<Categoria>,
    private val onClickCategoria: (cate : String ) -> Unit) : RecyclerView.Adapter<CategoriaAdapter.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val categoria= categories[position]
        Picasso.get().load(categoria.imgUrl).into(holder.imgurl)
        holder.description.text = categoria.nomCate

        holder.imgurl.setOnClickListener{
            onClickCategoria.invoke(categoria.codCate)
        }

    }

    override fun getItemCount() = categories.size



    class ViewHolder( itemView :View) : RecyclerView.ViewHolder(itemView) {
        val imgurl : ImageView= itemView.findViewById(R.id.imgViewcategoria)
        val description : TextView = itemView.findViewById(R.id.desCategoria)
    }
}