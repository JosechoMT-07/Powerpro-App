package net.josesernamacia.powerpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CuadroActivity : AppCompatActivity() {
    private data class ElectricElement(val name: String, val quantity: Int, val price: Double)

    private val elementsList = mutableListOf<ElectricElement>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuadro)

        val edtNombreElemento: EditText = findViewById(R.id.edtNombreElemento)
        val edtCantidad: EditText = findViewById(R.id.edtCantidad)
        val edtPrecio: EditText = findViewById(R.id.edtPrecio)
        val btAddComponent: Button = findViewById(R.id.btAddComponent)
        val btResumen: Button = findViewById(R.id.btResumen)
        val tvMostrarResumen: TextView = findViewById(R.id.tvMostrarResumen)

        btAddComponent.setOnClickListener {
            val nombre = edtNombreElemento.text.toString()
            val cantidadStr = edtCantidad.text.toString()
            val cantidad = cantidadStr.toIntOrNull()
            val precioStr = edtPrecio.text.toString()
            val precio = precioStr.toDoubleOrNull()

            if (nombre.isNotEmpty() && cantidad != null && precio != null) {
                // Agregar el elemento a la lista
                elementsList.add(ElectricElement(nombre, cantidad, precio))
                // Limpiar los campos de entrada
                edtNombreElemento.text.clear()
                edtCantidad.text.clear()
                edtPrecio.text.clear()
            }
        }

        btResumen.setOnClickListener {
            // Calcular el costo total
            val costoTotal = elementsList.sumOf { it.quantity * it.price }
            // Mostrar el resumen en el textView
            val resumenText = StringBuilder()
            for (elemento in elementsList) {
                resumenText.append("${elemento.name} - ${elemento.quantity} unidades - Precio unitario: ${String.format("%.2f", elemento.price)}\n")
            }
            resumenText.append("Costo total: ${String.format("%.2f", costoTotal)}")
            tvMostrarResumen.text = resumenText.toString()
        }
    }
}