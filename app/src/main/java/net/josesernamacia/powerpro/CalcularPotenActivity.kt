package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CalcularPotenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_poten)

        val edtPotencia: EditText = findViewById(R.id.edtPotencia)
        val edtVoltaje: EditText = findViewById(R.id.edtVoltaje)
        val btCalcularSeccion: Button = findViewById(R.id.btCalcularSeccion)
        val textViewResultado: TextView = findViewById(R.id.textView2)

        btCalcularSeccion.setOnClickListener {
            val potenciaStr = edtPotencia.text.toString()
            val voltajeStr = edtVoltaje.text.toString()

            if (potenciaStr.isNotEmpty() && voltajeStr.isNotEmpty()) {
                val potencia = potenciaStr.toDouble()
                val voltaje = voltajeStr.toDouble()
                val caidaVoltajePermitida = 0.03 * voltaje // Suponiendo una caída del 3%
                val corriente = potencia / voltaje
                val longitudMaxima = potencia / (corriente * caidaVoltajePermitida)

                textViewResultado.text = "Longitud máxima del cable: ${String.format("%.2f", longitudMaxima)} metros"
            } else {
                textViewResultado.text = "Por favor, ingresa la potencia y el voltaje requeridos."
            }
        }


    }
}