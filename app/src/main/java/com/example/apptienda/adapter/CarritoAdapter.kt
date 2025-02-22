package com.example.apptienda.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apptienda.R
import com.example.apptienda.model.Productos.ProductosJSON

class CarritoAdapter (private val listaCarrito:ArrayList<ProductosJSON>, private val context: Context): RecyclerView.Adapter<CarritoAdapter.MyHolder>() {

    //Sacamos cada elemento de la vista
    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen: ImageView = itemView.findViewById(R.id.imagenProductoCarrito)
        val nombre: TextView = itemView.findViewById(R.id.nombreProductoCarrito)
        val precio: TextView = itemView.findViewById(R.id.nombrePrecioCarrito)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // Asociamos XML al Holder
        val vista: View = LayoutInflater.from(context).inflate(R.layout.item_carrito, parent, false)
        return MyHolder(vista) // Retornamos a este holder la vista creada

    }

    override fun getItemCount(): Int {
        return listaCarrito.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val product: ProductosJSON = listaCarrito[position]
        Glide.with(holder.itemView.context).load(product.thumbnail).into(holder.imagen)
        holder.nombre.text = product.title
        holder.precio.text = "${product.price} €"
    }
}