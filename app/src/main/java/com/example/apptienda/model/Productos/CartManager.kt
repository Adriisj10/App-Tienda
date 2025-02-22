package com.example.apptienda.model.Productos

import java.io.Serializable

class CartManager() : Serializable{
    // Creamos arraylist para almacenar los productos que han sido agregados al pulsar el boton agregar al carrito
    private val productosCarrito: MutableList <ProductosJSON> = mutableListOf()

    // Funcion para añadir productos
    fun addProduct (product: ProductosJSON){
        productosCarrito.add(product)
    }
    // Funcion para obtener productos
    fun getProductosCarrito(): ArrayList<ProductosJSON>{
        return ArrayList(productosCarrito)
    }

    // Funcion para limpiar productos
    fun clearProductosCarrito(){
        productosCarrito.clear()
    }


}