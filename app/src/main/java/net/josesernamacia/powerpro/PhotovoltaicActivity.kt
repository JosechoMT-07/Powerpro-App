package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.josesernamacia.powerpro.databinding.ActivityPhotovoltaicBinding
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos


class PhotovoltaicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotovoltaicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotovoltaicBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonCalculate.setOnClickListener {
            val powerInstalled = binding.edtPowerInversor.text.toString().toDoubleOrNull()
            val panelType = binding.spinnerPanelType.selectedItem.toString()
            val orientation = binding.edtOrientationPanel.text.toString().toDoubleOrNull()
            val inclination = binding.edtInclinationPanel.text.toString().toDoubleOrNull()
            val efficiency = binding.edtEfficiencySystem .text.toString().toDoubleOrNull()

            if (powerInstalled != null && orientation != null && inclination != null && efficiency != null) {
                val hoursOfSun = 1500 // Ejemplo de horas equivalentes de sol al año

                // Convertir la orientación e inclinación a radianes
                val orientationRad = orientation * PI / 180
                val inclinationRad = inclination * PI / 180

                // Supongamos que los diferentes tipos de paneles tienen diferentes eficiencias
                val efficiencyFactor = when (panelType) {
                    "Panel tipo A" -> 1.1 // Ajuste de eficiencia para el panel tipo A
                    "Panel tipo B" -> 1.0 // Eficiencia estándar para el panel tipo B
                    "Panel tipo C" -> 0.9 // Ajuste de eficiencia para el panel tipo C
                    else -> 1.0 // Eficiencia estándar si no se especifica un tipo de panel
                }

                // Convertir la eficiencia del porcentaje a una fracción
                val efficiencyFraction = efficiency / 100.0

                // Ajustar la eficiencia por el tipo de panel
                val adjustedEfficiency = efficiencyFraction * efficiencyFactor

                // Calcular la cantidad de luz solar incidente en el panel solar
                val incidence = abs(cos(inclinationRad) * cos(orientationRad)).coerceIn(0.0, 1.0)

                // Calcular la producción anual
                val annualProduction = powerInstalled * hoursOfSun * adjustedEfficiency * incidence
                binding.tvResult.text = "Producción anual estimada: ${String.format("%.2f", annualProduction)} kWh"
            } else {
                binding.tvResult.text = "Por favor ingrese valores válidos para todos los campos."
            }
        }
    }
}