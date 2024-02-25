package com.example.firstappactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.ViewUtils.getBackgroundColor
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class MiSaludActivity : AppCompatActivity() {

    //lamar desde aqui a las Vista de seleccion de mujer y hombre creando dos variables en modo privado:
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false

    //Poner en valor al peso por defecto
    private var currentWeight: Int = 45

    //Poner un valor por defecto a la Altura
    private var currenteHeight: Double = 120.0

    //POner un valor por defecto a Edad
    private var currentEdge: Int = 10

    /**
     *llamar a las dos vistas $viewMale y $viewFemale  poniendo cualquier nombre y el tipo de  objeto que corresponde
     * EL método $lateinit significa: te inciaras cuando yo de la orden
     */
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView

    //llamando al texto donde se muestra el valor de la altura
    private lateinit var tvHeight: TextView

    //llamando al la barra contadora
    private lateinit var rsHeight: RangeSlider

    //llamando a los botondes de + y - para peso
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeigth: FloatingActionButton
    private lateinit var tvWeigth: TextView

    //llamando los botones de Edad
    private lateinit var btnSubtrackEdge: FloatingActionButton
    private lateinit var btnPlusEdge: FloatingActionButton
    private lateinit var tvEdge: TextView

    //Llamando boton Calcular
    private lateinit var btnCalculate: Button

    //Crear unac constante que permita ser usada en la funcion $navigateToResult para llamar a la la ventana $ResulIMCActivity
    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_salud)

        initComponents()
        initListeners()
        iniUI()
    }

    //Función que permite iniciar los componentes:
    private fun initComponents() {
        //Iniciar los 2 componentes de las CardView que corresponde a genero masculino y femenino (para Card View genero)
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnPlusWeigth = findViewById(R.id.btnPlusWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        tvWeigth = findViewById(R.id.tvWeight)
        btnPlusEdge = findViewById(R.id.btnPlusEdge)
        btnSubtrackEdge = findViewById(R.id.btnSubTrackEdge)
        tvEdge = findViewById(R.id.tvEdge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    //Funcion que pone en escucha a los componentes
    private fun initListeners() {
        //Declaracion que pone en escucha para cambiar de color al hacer el click sobre las CarView de masculino y femenino
        viewMale.setOnClickListener {
            cambiarGenero()
            cambiarColorGenero()
        }
        viewFemale.setOnClickListener {
            cambiarGenero()
            cambiarColorGenero()
        }
        // declaracion que pone en escucha a Range Slider de la altura

        rsHeight.addOnChangeListener { _, value, _ ->
            //formatear los decimales el valor de la altura
            val df = DecimalFormat("#.##")
            currenteHeight = (df.format(value).toDouble() / 100)  //convirtiendo a metros
            tvHeight.text = "$currenteHeight metros"

        }
        //Declaracion que pone en escucha a los botenes y text view de Peso
        btnPlusWeigth.setOnClickListener {
            //al valor por defecto se suma 1: currentWeight = currenteWeight +1
            // O tambien se puede expresar: currentWieght += 1
            currentWeight += 1

            //con este metodo se podra poner el numero el el textView
            setWeight()
        }

        btnSubtractWeight.setOnClickListener {

            //al valor por defecto se resta 1: currentWeight = currenteWeight -1
            // O tambien se puede expresar: currentWeight -= 1
            currentWeight -= 1
            setWeight()
        }
        //Declaracion que pone en escucha a los botenes y text view de Edad
        btnSubtrackEdge.setOnClickListener {
            currentEdge -= 1
            setEdge()
        }
        btnPlusEdge.setOnClickListener {
            currentEdge += 1
            setEdge()
        }

        //Declaraacion que pone en escucha el boton Calculate
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    // con esta funcion se lleva el resultado de calculo de IMC  a la ventana ResultadoIMCActivity
    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResutlIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result) //Se usara la constante
        startActivity(intent)
    }

    //Funcion a que permite poner el valor de Peso en el textView
    private fun setWeight() {
        tvWeigth.text = currentWeight.toString()
    }

    //Funcion q permite poner el valor de edad en el textView
    private fun setEdge() {
        tvEdge.text = currentEdge.toString()
    }

    //Funcion que cambia la selección con el click entre las cardview de genero
    private fun cambiarGenero() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    //Funcion que cambia el color de las $carview de genero:
    private fun cambiarColorGenero() {
        viewMale.setCardBackgroundColor(conseguirBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(conseguirBackgroundColor(isFemaleSelected))
    }

    //(para Card View genero)
    private fun conseguirBackgroundColor(SeleccionComponente: Boolean): Int {
        val colorReferencia = if (SeleccionComponente) {
            R.color.backgroud_component_selected
        } else {
            R.color.backgroud_component
        }
        return ContextCompat.getColor(this, colorReferencia)
    }

    //función de calculo de ICM
    private fun calculateIMC(): Double {
        //formula de IMC:peso/altura²

        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currenteHeight * currenteHeight)
        return df.format(imc).toDouble()  //retorna el resultado para poder ver en otra ventana

        //Log.i("Richard","el imc es $result") //con esta linea podemos imprimir el resultado para probar en el Log de Studio

    }


    private fun iniUI() {
        cambiarColorGenero()
        setWeight()
        setEdge()
    }
}