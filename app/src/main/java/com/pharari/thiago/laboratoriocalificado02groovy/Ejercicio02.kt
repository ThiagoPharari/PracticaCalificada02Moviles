package com.pharari.thiago.laboratoriocalificado02groovy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Ejercicio02 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio02)

        // Referencias a los elementos del layout
        val nombreCliente = findViewById<EditText>(R.id.etNombreCliente)
        val numeroCliente = findViewById<EditText>(R.id.etNumeroCliente)
        val productos = findViewById<EditText>(R.id.etProductos)
        val ciudad = findViewById<EditText>(R.id.etCiudad)
        val direccion = findViewById<EditText>(R.id.etDireccion)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        // Acción cuando se presiona el botón Registrar
        btnRegistrar.setOnClickListener {
            val nombre = nombreCliente.text.toString()
            val numero = numeroCliente.text.toString()
            val listaProductos = productos.text.toString()
            val ciudadText = ciudad.text.toString()
            val direccionText = direccion.text.toString()

            // Validación de los campos
            if (nombre.isEmpty() || numero.isEmpty() || listaProductos.isEmpty() ||
                ciudadText.isEmpty() || direccionText.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Si los campos están completos, se abre la nueva actividad con los datos
                val intent = Intent(this, DetallePedidoActivity::class.java).apply {
                    putExtra("nombreCliente", nombre)
                    putExtra("numeroCliente", numero)
                    putExtra("productos", listaProductos)
                    putExtra("ciudad", ciudadText)
                    putExtra("direccion", direccionText)
                }
                startActivity(intent)
            }
        }
    }
}
