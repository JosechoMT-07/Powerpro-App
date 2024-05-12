package net.josesernamacia.powerpro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import net.josesernamacia.powerpro.R


class YouFragment : Fragment() {

    private lateinit var tvEmailUser: TextView
    private lateinit var tvNameUser: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_you, container, false)
        tvEmailUser = view.findViewById(R.id.tvEmailUser)

        return view
    }

    fun setEmail(email: String){
        tvEmailUser.text = email
    }

}