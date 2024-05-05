package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos


class PhotovoltaicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photovoltaic)

        val edtPower: EditText = findViewById(R.id.edtPowerInversor)
        val spinnerPanelType: Spinner = findViewById(R.id.spinnerPanelType)
        val edtOrientation: EditText = findViewById(R.id.edtOrientationPanel)
        val edtInclination: EditText = findViewById(R.id.edtInclinationPanel)
        val edtEfficiency: EditText = findViewById(R.id.edtEfficiencySystem)
        val btnCalculate: Button = findViewById(R.id.buttonCalculate)
        val tvResult: TextView = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val powerInstalled = edtPower.text.toString().toDoubleOrNull()
            val panelType = spinnerPanelType.selectedItem.toString()
            val orientation = edtOrientation.text.toString().toDoubleOrNull()
            val inclination = edtInclination.text.toString().toDoubleOrNull()
            val efficiency = edtEfficiency.text.toString().toDoubleOrNull()

            if (powerInstalled != null && orientation != null && inclination != null && efficiency != null) {
                // Supongamos que conocemos las horas equivalentes de sol al año para la ubicación geográfica
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
                tvResult.text = "Producción anual estimada: ${String.format("%.2f", annualProduction)} kWh"
            } else {
                tvResult.text = "Por favor ingrese valores válidos para todos los campos."
            }
        }
    }
}