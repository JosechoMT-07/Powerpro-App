package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class CalcularAmpeActivity : AppCompatActivity() {

    // Resistividad en ohmios por metro a 20 grados Celsius
    val RESISTIVIDAD_COBRE = 1.724e-8
    val RESISTIVIDAD_ALUMINIO = 2.82e-8

    // Factor de seguridad para la caída de voltaje (por ejemplo, 3%)
    val FACTOR_SEGURIDAD = 0.03

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_ampe)

        val edtAmperaje: EditText = findViewById(R.id.edtAmperaje)
        val spinMaterial: Spinner = findViewById(R.id.spinMaterial)
        val btCalcDiamA: Button = findViewById(R.id.btCalcDiamA)
        val tvResultado: TextView = findViewById(R.id.tvResultadoDiametro)




        btCalcDiamA.setOnClickListener {
            // Obtener el amperaje ingresado por el usuario
            val amperajeStr = edtAmperaje.text.toString()
            if (amperajeStr.isNotEmpty()) {
                val amperaje = amperajeStr.toDouble()

                // Obtener el material de cable seleccionado
                val materialCable = spinMaterial.selectedItem.toString()

                // Realizar los cálculos según el material seleccionado y el amperaje ingresado
                val diametroCable = calcularDiametroCable(materialCable, amperaje)

                // Mostrar el resultado en el TextView
                tvResultado.text = "El diámetro del cable necesario es: ${String.format("%.2f", diametroCable)}  mm"
            } else {
                // Manejar el caso en que el campo de amperaje esté vacío
                tvResultado.text = "Por favor ingrese el amperaje del circuito."
            }
        }


    }

    private fun calcularDiametroCable(material: String, amperaje: Double): Double {
        // Tabla de tamaños de cable y su capacidad de corriente en amperios
        val tablaCables = mapOf(
            0.75 to 7.0,
            1.0 to 10.0,
            1.5 to 15.0,
            2.5 to 20.0,
            4.0 to 25.0,
            6.0 to 30.0,
            10.0 to 40.0,
            16.0 to 50.0,
            25.0 to 63.0,
            35.0 to 80.0,
            50.0 to 100.0,
            70.0 to 125.0,
            95.0 to 160.0,
            120.0 to 180.0,
            150.0 to 200.0,
            185.0 to 220.0,
            240.0 to 250.0,
            300.0 to 300.0,
            400.0 to 340.0
        )

        // Obtener la capacidad de corriente del tamaño de cable adecuado para el amperaje dado
        val capacidadCorriente = tablaCables.entries.firstOrNull { it.value >= amperaje }?.key ?: 0.0

        // Si no se encuentra ningún tamaño de cable adecuado, devolver 0.0
        if (capacidadCorriente == 0.0) {
            return 0.0
        }

        return capacidadCorriente
    }

    private fun redondearDiametro(diametro: Double): Double {
        // Tamaños comunes de diámetro de cable en mm
        val tamanios = arrayOf(0.75, 1.00, 1.5, 2.5, 4.0, 6.0, 10.0, 16.0, 25.0, 35.0, 50.0, 70.0, 95.0, 120.0, 150.0, 185.0, 240.0, 300.0, 400.0)

        // Encuentra el tamaño de cable más cercano al diámetro calculado
        var diametroRedondeado: Double = tamanios[0]
        for (tamano in tamanios) {
            if (tamano >= diametro) {
                diametroRedondeado = tamano
                break
            }
        }

        return diametroRedondeado
    }

}