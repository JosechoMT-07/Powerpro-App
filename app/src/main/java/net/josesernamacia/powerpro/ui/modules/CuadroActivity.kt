package net.josesernamacia.powerpro.ui.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.databinding.ActivityCuadroBinding

class CuadroActivity : AppCompatActivity() {
    private data class ElectricElement(val name: String, val quantity: Int, val price: Double)
    private val elementsList = mutableListOf<ElectricElement>()
    private lateinit var binding: ActivityCuadroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuadroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAddComponent.setOnClickListener {
            val nombre = binding.edtNombreElemento.text.toString()
            val cantidadStr = binding.edtCantidad.text.toString()
            val cantidad = cantidadStr.toIntOrNull()
            val precioStr = binding.edtPrecio.text.toString()
            val precio = precioStr.toDoubleOrNull()

            if (nombre.isNotEmpty() && cantidad != null && precio != null) {
                // Agregar el elemento a la lista
                elementsList.add(ElectricElement(nombre, cantidad, precio))
                // Limpiar los campos de entrada
                binding.edtNombreElemento.text.clear()
                binding.edtCantidad.text.clear()
                binding.edtPrecio.text.clear()
            }
        }

        binding.btResumen.setOnClickListener {
            // Calcular el costo total
            val costoTotal = elementsList.sumOf { it.quantity * it.price }
            // Mostrar el resumen en el textView
            val resumenText = StringBuilder()
            for (elemento in elementsList) {
                resumenText.append(
                    getString(
                        R.string.unidades_precio_unitario,
                        elemento.name,
                        elemento.quantity,
                        String.format("%.2f", elemento.price)
                    ))
            }
            resumenText.append(getString(R.string.costo_total, String.format("%.2f", costoTotal)))
            binding.tvMostrarResumen.text = resumenText.toString()
        }
    }
}