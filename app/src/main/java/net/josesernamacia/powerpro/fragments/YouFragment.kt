package net.josesernamacia.powerpro.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import net.josesernamacia.powerpro.MainActivity
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.autentication.AuthActivity


class YouFragment : Fragment() {

    lateinit var tvEmailUser: TextView
    lateinit var tvNameUser: TextView
    lateinit var ivLogOut: ImageView
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layou7t for this fragment
        val view = inflater.inflate(R.layout.fragment_you, container, false)
        tvEmailUser = view.findViewById(R.id.tvEmailUser)
        ivLogOut = view.findViewById(R.id.btnLogOut)

        mainActivity = MainActivity()



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivLogOut.setOnClickListener {
            mainActivity.logOutSesion()
        }
    }



    fun setEmail(email: String){
        tvEmailUser.text = email
    }

}