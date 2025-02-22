package com.example.apptienda

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptienda.adapter.CarritoAdapter
import com.example.apptienda.databinding.ActivitySecondBinding
import com.example.apptienda.model.Productos.CartManager
import com.example.apptienda.model.Productos.ProductosJSON
import com.google.android.material.snackbar.Snackbar

class SecondActivity : AppCompatActivity() {
        private lateinit var binding: ActivitySecondBinding
        private lateinit var adapter: CarritoAdapter
        private var listaCarrito: ArrayList<ProductosJSON> = arrayListOf() // creo arraylist vacia

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySecondBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Recibir la lista de productos con intent
            listaCarrito = intent.getSerializableExtra("carrito") as? ArrayList<ProductosJSON> ?: arrayListOf()// Recibimos el carrito
            instancias()


        }


        private fun instancias() {
            setSupportActionBar(binding.toolbarCarrito) // pasa de ser grafico a tambien ser logico
            adapter = CarritoAdapter(listaCarrito,this)
            binding.recyclerCarrito.adapter = adapter
            if (resources.configuration.orientation ==1) { //orientation 1 pantalla en vertical, por eso ponemos linear para que sea de uno en uno
                binding.recyclerCarrito.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            } else if (resources.configuration.orientation ==2){ // orientation 2 pantalla en horizontal, por eso ponemos grid con 2 columnas para que se vea de 2 en 2
                binding.recyclerCarrito.layoutManager = GridLayoutManager(this,2, GridLayoutManager.HORIZONTAL,false)
            }
            precioTotal(listaCarrito)


        }

         private fun precioTotal(productosCarrito: List<ProductosJSON>){
        val total = productosCarrito.sumOf { it.price?.toDoubleOrNull() ?: 0.0 }
        binding.precioCarrito.text = "Precio total: $total €"
        }

         override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second_menu, menu)
        return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {

            return when (item.itemId){
                R.id.menuConfirmarCompra -> {
                    val totalCompra = listaCarrito.sumOf { it.price?.toDoubleOrNull() ?:0.0 }
                    Snackbar.make(binding.recyclerCarrito,"Enhorabuena, compra realizaza por el valor de : ${totalCompra} €",Snackbar.LENGTH_SHORT).show()
                    true
                }
                R.id.menuVaciarCarrito ->{
                    listaCarrito.clear()
                    adapter.notifyDataSetChanged()
                    binding.precioCarrito.text = "Precio total: 0.0 €"

                    // Tambien vaciamos CartManager que es donde se almacenan los productos seleccionados del carrito
                    val cartManager = CartManager()
                    cartManager.clearProductosCarrito()

                    Snackbar.make(binding.recyclerCarrito,"Carrito vaciado", Snackbar.LENGTH_SHORT).show()
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
}

