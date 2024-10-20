package com.pharari.thiago.laboratoriocalificado02groovy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetallePedidoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pedido)

        // Obtener datos pasados del intent
        val nombreCliente = intent.getStringExtra("nombreCliente")
        val numeroCliente = intent.getStringExtra("numeroCliente")
        val productos = intent.getStringExtra("productos")
        val direccion = intent.getStringExtra("direccion")

        // Referencias a los elementos del layout
        val tvNombreCliente = findViewById<TextView>(R.id.tvNombreCliente)
        val tvNumeroCliente = findViewById<TextView>(R.id.tvNumeroCliente)
        val tvProductos = findViewById<TextView>(R.id.tvProductos)
        val tvDireccion = findViewById<TextView>(R.id.tvDireccion)
        val btnLlamar = findViewById<Button>(R.id.btnLlamar)
        val btnWhatsapp = findViewById<Button>(R.id.btnWhatsapp)
        val btnMaps = findViewById<Button>(R.id.btnMaps)

        // Setear valores en los TextViews
        tvNombreCliente.text = nombreCliente ?: "Desconocido"
        tvNumeroCliente.text = numeroCliente ?: "No proporcionado"
        tvProductos.text = productos ?: "No especificado"
        tvDireccion.text = direccion ?: "No proporcionado"

        // Verificar si el número de cliente es válido antes de intentar realizar acciones
        if (numeroCliente.isNullOrEmpty()) {
            btnLlamar.isEnabled = false
            btnWhatsapp.isEnabled = false
            Toast.makeText(this, "Número de cliente no disponible", Toast.LENGTH_SHORT).show()
        }

        // Acción para llamar
        btnLlamar.setOnClickListener {
            if (!numeroCliente.isNullOrEmpty()) {
                val intentLlamar = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$numeroCliente")
                }
                startActivity(intentLlamar)
            } else {
                Toast.makeText(this, "Número de cliente no válido", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción para WhatsApp
        btnWhatsapp.setOnClickListener {
            if (!numeroCliente.isNullOrEmpty()) {
                val mensaje = "Hola $nombreCliente, tus productos: $productos están en camino a la dirección: $direccion."
                val phone = "+51$numeroCliente"  // Añadiendo código de país
                val uri = Uri.parse("https://wa.me/$phone?text=${Uri.encode(mensaje)}")

                val intentWhatsapp = Intent(Intent.ACTION_VIEW, uri)
                try {
                    startActivity(intentWhatsapp)
                } catch (e: Exception) {
                    Toast.makeText(this, "No se pudo abrir WhatsApp, asegúrate de que esté instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Número de cliente no válido para WhatsApp", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción para Google Maps
        btnMaps.setOnClickListener {
            if (!direccion.isNullOrEmpty()) {
                val uri = Uri.parse("geo:0,0?q=${Uri.encode(direccion)}")
                val intentMaps = Intent(Intent.ACTION_VIEW, uri).apply {
                    setPackage("com.google.android.apps.maps")
                }

                try {
                    startActivity(intentMaps)
                } catch (e: Exception) {
                    Toast.makeText(this, "No se pudo abrir Google Maps, asegúrate de que esté instalado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Dirección no válida para Google Maps", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

