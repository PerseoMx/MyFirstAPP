package com.example.firstappactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.firstappactivity.MiSaludActivity.Companion.IMC_KEY

class ResutlIMCActivity : AppCompatActivity() {

    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescripcion:TextView
    private lateinit var btnRecalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resutl_imcactivity)

        val result:Double = intent.extras?.getDouble(IMC_KEY)?: -1.0
        initComponent()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener{onBackPressed()} //onBackPressed() permite retornar una ventana atrás
    }

    private fun initUI(result: Double) {
        tvIMC.text=result.toString()

        when(result){

            in 0.00 .. 18.5 ->{ //Bajo en Peso
                tvResult.text=getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescripcion.text=getString(R.string.description_bajo_peso)
            }
            in 18.51 .. 24.99->{ //Peso Normal
                tvResult.text=getString(R.string.title_peso_normar)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescripcion.text=getString(R.string.description_peso_normal)
            }
            in 25.00 .. 29.99->{ //Sobre peso

                tvResult.text=getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.sobrepeso))
                tvDescripcion.text=getString(R.string.description_soprepeso)
            }
            in 30.00 ..99.00->{ //Obesidad
                tvResult.text=getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescripcion.text=getString(R.string.description_obesidad)
            }
            else ->{ //Error
                tvIMC.text=getString(R.string.error) //para llamar del archivo string desde otra ventana se debe llamar a traves del metodo getString
                tvResult.text=getString(R.string.error)
                tvDescripcion.text=getString(R.string.error)

            }
        }
    }

    private fun initComponent() {
        tvIMC=findViewById(R.id.tvIMC)
        tvResult=findViewById(R.id.tvResult)
        tvDescripcion=findViewById(R.id.tvDescripcion)
        btnRecalculate=findViewById(R.id.btnReCalculate)

    }
}