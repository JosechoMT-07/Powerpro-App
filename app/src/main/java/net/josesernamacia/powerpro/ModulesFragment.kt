package net.josesernamacia.powerpro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class ModulesFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_modules, container, false)
        val btn: Button = v.findViewById(R.id.btSolar)
        btn.setOnClickListener {
            val intent = Intent(context, PhotovoltaicActivity::class.java)
            startActivity(intent)
        }
        return v
    }

}