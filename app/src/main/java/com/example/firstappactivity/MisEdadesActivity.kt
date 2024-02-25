package com.example.firstappactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MisEdadesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_edades)
        val miCalculo: AppCompatButton = findViewById(R.id.btnCalculo)
        val miEdad: EditText = findViewById(R.id.etxtEdad)
        val miResultado: TextView = findViewById(R.id.vtxtResultado)

        miCalculo.setOnClickListener {

            val numeroResultado = miEdad.text.toString()
                .toIntOrNull()  //este metodo permite decidir si el valor ingresado es integral o nullo

            if (numeroResultado == null) {
                miResultado.text = "Escriba un valor valido"
            } else if (numeroResultado < 18) {

                miResultado.text = "Eres menor de edad"
            } else if (numeroResultado > 18) {
                miResultado.text = "Eres mayor de 18"
            } else {
                miResultado.text = "Tienes justo 18 años"
            }
        }

    }
}