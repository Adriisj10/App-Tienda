package com.example.apptienda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.apptienda.adapter.CategoriasAdapter
import com.example.apptienda.databinding.ActivityMainBinding
import com.example.apptienda.model.Productos.CartManager
import com.example.apptienda.model.Productos.ProductosJSON
import org.json.JSONArray
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CategoriasAdapter //Objeto del adaptador
    private val listaProductos = arrayListOf<ProductosJSON>()
    private val cartManager = CartManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()

        // Creamos objeto JSON donde se solicita la informacion
        val peticion: JsonObjectRequest =
            JsonObjectRequest("https://dummyjson.com/products", //Primer parametro lo que quiero consultr, en este caso la URL
                {  //Funcion por eso estan los corchetes, se trata cuando responde bien
                    val array: JSONArray =
                        it.getJSONArray("products") // creamos array del JSOn que es donde va a buscar los datos
                    for (i in 0..array.length() - 1) { // Recorremos la array del JSON para encontrar los datos que queremos
                        // sacamos los datos que nos interesan primero creamos de donde se saca el producto y luego lo que nos interesa
                        val productoJSON = array.getJSONObject(i)
                        val producto = ProductosJSON(
                            title = productoJSON.getString("title"),
                            price = productoJSON.getDouble("price").toString(),
                            thumbnail = productoJSON.getString("thumbnail")
                        )
                        listaProductos.add(producto)

                        Log.d(
                            "RecyclerView",
                            "Lista de productos en la app: ${listaProductos.size}"
                        )
                    }

                    adapter.notifyDataSetChanged() //notificar cambios al recycler view


                },


                { Log.v("datos", it.toString()) })
        Volley.newRequestQueue(applicationContext).add(peticion) // para lanzar la peticion
    }

    private fun instancias() {

        setSupportActionBar(binding.toolbar) // pasa de ser grafico a tambien ser logico
        adapter = CategoriasAdapter(listaProductos, this, cartManager)
        // Asocio el Recycler View con el adaptador
        binding.recyclerProductos.adapter = adapter
        if (resources.configuration.orientation == 1) { //orientation 1 pantalla en vertical, por eso ponemos linear para que sea de uno en uno
            binding.recyclerProductos.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        } else if (resources.configuration.orientation == 2) { // orientation 2 pantalla en horizontal, por eso ponemos grid con 2 columnas para que se vea de 2 en 2
            binding.recyclerProductos.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
        }
    }
    // Se crea esta funcion ara poder ver el menu, por eso creo el objeto arriba

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuCarrito -> {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("carrito", cartManager.getProductosCarrito())
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}