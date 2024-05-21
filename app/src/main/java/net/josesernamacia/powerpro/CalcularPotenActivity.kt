package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.josesernamacia.powerpro.databinding.ActivityCalcularPotenBinding

class CalcularPotenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalcularPotenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcularPotenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCalcularSeccion.setOnClickListener {
            val potenciaStr = binding.edtPotencia.text.toString()
            val voltajeStr = binding.edtVoltaje.text.toString()
            val porcentajePerdidaStr = binding.editTextNumberDecimal.text.toString()

            if (potenciaStr.isNotEmpty() && voltajeStr.isNotEmpty() && porcentajePerdidaStr.isNotEmpty()) {
                val potencia = potenciaStr.toDouble()
                val voltaje = voltajeStr.toDouble()
                val porcentajePerdida = porcentajePerdidaStr.toDouble() / 100 // Convertir el porcentaje a decimal
                val caidaVoltajePermitida = porcentajePerdida * voltaje
                val corriente = potencia / voltaje
                val longitudMaxima = potencia / (corriente * caidaVoltajePermitida)

                binding.textView2.text = "Longitud máxima del cable: ${String.format("%.2f", longitudMaxima)} metros"
            } else {
                binding.textView2.text = "Por favor, ingresa la potencia, el voltaje y el porcentaje de pérdida requeridos."
            }
        }



    }
}