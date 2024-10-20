package com.pharari.thiago.laboratoriocalificado02groovy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pharari.thiago.laboratoriocalificado02groovy.databinding.ActivityEjercicio01Binding

class Ejercicio01 : AppCompatActivity() {

    // Inicializamos el binding
    private lateinit var binding: ActivityEjercicio01Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos la vista utilizando binding
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón para mostrar la vista verde
        binding.buttonMostrar.setOnClickListener {
            binding.viewGreen.visibility = View.VISIBLE
        }

        // Botón para ocultar la vista verde
        binding.buttonOcultar.setOnClickListener {
            binding.viewGreen.visibility = View.INVISIBLE
        }
    }
}