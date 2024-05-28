package net.josesernamacia.powerpro.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.josesernamacia.powerpro.ui.modules.CalcularAmpeActivity
import net.josesernamacia.powerpro.ui.modules.CalcularPotenActivity
import net.josesernamacia.powerpro.ui.modules.CuadroActivity
import net.josesernamacia.powerpro.ui.modules.PhotovoltaicActivity
import net.josesernamacia.powerpro.databinding.FragmentModulesBinding


class ModulesFragment : Fragment() {
    private var _binding : FragmentModulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModulesBinding.inflate(inflater,container,false)

        binding.btSolar.setOnClickListener {
            val intent = Intent(context, PhotovoltaicActivity::class.java)
            startActivity(intent)
        }

        binding.btDmAmperaje.setOnClickListener {
            val intent = Intent(context, CalcularAmpeActivity::class.java)
            startActivity(intent)
        }

        binding.btDmPotencia.setOnClickListener {
            val intent = Intent(context, CalcularPotenActivity::class.java)
            startActivity(intent)
        }

        binding.btCuadroElectrico.setOnClickListener {
            val intent = Intent(context, CuadroActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}