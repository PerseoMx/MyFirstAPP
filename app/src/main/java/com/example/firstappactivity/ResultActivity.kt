package com.example.firstappactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Llamar alo sobjetos del XML

        val tvResult=findViewById<TextView>(R.id.txtResult)

        //cargar el texto desde la ventana de Main
        val name:String = intent.extras?.getString("NOMBRE_EXTRA").orEmpty()
        tvResult.text="Hola $name "

    }
}