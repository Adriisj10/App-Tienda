package com.example.apptienda.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apptienda.R
import com.example.apptienda.SecondActivity
import com.example.apptienda.model.Productos.CartManager
import com.example.apptienda.model.Productos.ProductosJSON
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class CategoriasAdapter(private val lista: ArrayList<ProductosJSON>, private val context: Context, private val cartManager: CartManager = CartManager()):RecyclerView.Adapter<CategoriasAdapter.MyHolder>(){



    //itemView -> pasar a vista el XML con todos sus elementos. inner para clase anidada
    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        // Sacamos cada elemento que quiera de la vista
        val imagen: ImageView = itemView.findViewById(R.id.imagenProducto)
        val nombre: TextView = itemView.findViewById(R.id.nombreProducto)
        val precio: TextView = itemView.findViewById(R.id.precioProducto)
        val boton: Button = itemView.findViewById(R.id.btnAgregar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // Asociamos XML al holder
        val vista:View = LayoutInflater.from(context).inflate(R.layout.item_productos,parent,false) // Referenciamos el XML
        return MyHolder(vista) // REtornamos en este MyHolder la vista que hemos creado antes donde se ha pintado el item_productos (XML)
    }

    override fun getItemCount(): Int {
        // Cuantas filas tengo que pintar
        return lista.size // Retornamos lista Arraylist

    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // pintara cada linea con su dato correspndiente
        val producto: ProductosJSON = lista[position]
        // Usamos Glide para cargar la imagen
        Glide.with(holder.itemView.context).load(producto.thumbnail).into(holder.imagen)
        holder.nombre.text = producto.title
        holder.precio.text = "${producto.price} €"
        holder.boton.setOnClickListener {
            cartManager.addProduct(producto) // Añadir el producto al carrito
            Snackbar.make(holder.boton,"Producto añadido al carrito",Snackbar.LENGTH_SHORT).show()
        }


    }



}