package net.josesernamacia.powerpro.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import net.josesernamacia.powerpro.CalcularAmpeActivity
import net.josesernamacia.powerpro.CalcularPotenActivity
import net.josesernamacia.powerpro.CuadroActivity
import net.josesernamacia.powerpro.PhotovoltaicActivity
import net.josesernamacia.powerpro.R


class ModulesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_modules, container, false)
        val btnSolar: Button = v.findViewById(R.id.btSolar)
        btnSolar.setOnClickListener {
            val intent = Intent(context, PhotovoltaicActivity::class.java)
            startActivity(intent)
        }
        val btnDiamAmp: Button = v.findViewById(R.id.btDmAmperaje)
        btnDiamAmp.setOnClickListener {
            val intent = Intent(context, CalcularAmpeActivity::class.java)
            startActivity(intent)
        }
        val btnDiamPot: Button = v.findViewById(R.id.btDmPotencia)
        btnDiamPot.setOnClickListener {
            val intent = Intent(context, CalcularPotenActivity::class.java)
            startActivity(intent)
        }
        val btnCuadroElec: Button = v.findViewById(R.id.btCuadroElectrico)
        btnCuadroElec.setOnClickListener {
            val intent = Intent(context, CuadroActivity::class.java)
            startActivity(intent)
        }


        return v
    }

}