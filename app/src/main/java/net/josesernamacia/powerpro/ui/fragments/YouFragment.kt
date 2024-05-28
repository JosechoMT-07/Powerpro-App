package net.josesernamacia.powerpro.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import net.josesernamacia.powerpro.MainActivity
import net.josesernamacia.powerpro.ui.autentication.AuthActivity
import net.josesernamacia.powerpro.databinding.FragmentYouBinding


class YouFragment : Fragment() {

    private var _binding : FragmentYouBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var auth: FirebaseAuth

    private val db = FirebaseFirestore.getInstance()
    private var emailUser: String? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYouBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()

        auth.currentUser?.let {
            emailUser = it.email
        }

        db.collection("users").document(emailUser.toString()).get().addOnSuccessListener {
            binding.edNameUser.setText(it.get("name") as String?)
        }

        binding.tvEmailUser.text = emailUser

        binding.btnSave.setOnClickListener {
            db.collection("users").document(emailUser.toString()).set(
                hashMapOf("name" to binding.edNameUser.text.toString())
            )
        }


        mainActivity = MainActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        registerEvents()
    }

    private fun registerEvents() {
        binding.btnLogOut.setOnClickListener {
            auth.signOut()

            val intent = Intent(activity, AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity?.finish()
        }
    }

}