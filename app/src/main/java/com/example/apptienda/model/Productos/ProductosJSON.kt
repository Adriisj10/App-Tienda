package com.example.apptienda.model.Productos

import java.io.Serializable

class ProductosJSON(var title: String? = null, var thumbnail: String, var price: String? = null, var category: String? =null): Serializable { // Ponemos nul porque si no pasa el dato se pone el valor nulo
}