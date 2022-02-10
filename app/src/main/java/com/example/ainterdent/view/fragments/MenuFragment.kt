
package com.example.ainterdent.view.fragments

import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ainterdent.view.CategoriasActivity
import com.example.ainterdent.view.ProductActivity
import com.example.ainterdent.R
import com.example.ainterdent.adapter.CategoriaAdapter
import com.example.ainterdent.adapter.PopularAdapter
import com.example.ainterdent.model.Producto
import com.example.ainterdent.view.ProductDetail
import com.example.ainterdent.viewmodel.MenuFragmenViewModel

import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment() {

    lateinit var viewModel : MenuFragmenViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val root = inflater.inflate(R.layout.fragment_menu, container, false)


        val imageSlider = root.image_slider
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1cZgEpjjTvdm4JCK9u0hgTS-Imc47aE-aFfGpFdy8wl8vkve1i44SKh_CWhBuiefrRcs&usqp=CAU","Promociones Navideñas"))
        imageList.add(SlideModel("https://thumbs.dreamstime.com/b/banner-de-anuncios-pasta-dental-negra-con-cepillo-y-diente-dientes-sanos-tubo-pl%C3%A1stico-afiche-promocional-marca-productos-195274580.jpg","Pasta Dental Black"))
        imageList.add(SlideModel("https://http2.mlstatic.com/D_NQ_NP_779015-MLM43020755343_082020-W.jpg","Colgate Luminose White"))
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        viewModel = ViewModelProvider(this).get(MenuFragmenViewModel::class.java)
        viewModel.categorias.observe(requireActivity(), Observer {
            rec_category.apply {
                layoutManager  = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                adapter = CategoriaAdapter(it){extraCategoria ->
                    val intent = Intent(activity, CategoriasActivity::class.java)
                    intent.putExtra("categoriaId",extraCategoria)
                    context.startActivity(intent)
                }
            }
        })
        viewModel.loadCategory()

        root.etVerTodo.setOnClickListener{
            val inten = Intent(activity, ProductActivity::class.java)
            startActivity(inten)
        }


        return  root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MenuFragmenViewModel::class.java)
        viewModel.productos.observe(requireActivity(), Observer {
            loadRecyclerviewProduct(it)
        })
        viewModel.loadProducts()




    }

    fun loadRecyclerviewProduct(products : List<Producto>) {
        new_product_rec.apply {
            layoutManager = GridLayoutManager(activity,2)

            adapter = PopularAdapter(products){ extraId , photoView ->
                val intent = Intent(activity, ProductDetail::class.java)
                intent.putExtra("id", extraId)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation( activity as AppCompatActivity, photoView,"photoToAnimate")
                context.startActivity(intent, options.toBundle())
            }
        }

    }



}