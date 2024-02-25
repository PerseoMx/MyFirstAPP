package com.example.firstappactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //llamar al los objetos del XML
        val btnInicio = findViewById<AppCompatButton>(R.id.btnIniciar)
        val etxtNombre = findViewById<AppCompatEditText>(R.id.EtxtNombre)
        val miCalculo = findViewById<AppCompatButton>(R.id.btnCalculoEdad)
        val miICM = findViewById<Button>(R.id.btnVentanaICM)

        //llamar al boton que escuche un click
        miICM.setOnClickListener{navegarMiSalud()}


        //Abrir ventana de calculo de edad
        miCalculo.setOnClickListener {
            val interaccion01 = Intent(this, MisEdadesActivity::class.java)
            startActivity(interaccion01)
        }


        // accion del boton
        btnInicio.setOnClickListener {

            //variable q permite introducir texto en el edittxt
            val name = etxtNombre.text.toString()

            if (name.isNotEmpty()) {
                //Log.i("Richard", "pulsaste el boton: $name")  //esta linea permite imprimir el mesnaje en Log

                //la siguiente linea permite navegar o ir a otra ventana
                val interaccion = Intent(this, ResultActivity::class.java)

                //llevar valores a la otra ventana con la palabra reservada putExtra
                interaccion.putExtra("NOMBRE_EXTRA", name)
                startActivity(interaccion)

            }

        }

    }
    private fun navegarMiSalud(){

//Abrir ventana de miSaludActivity a traves de una funcion
        val interaccion02= Intent(this,MiSaludActivity::class.java)
        startActivity(interaccion02)

    }
}