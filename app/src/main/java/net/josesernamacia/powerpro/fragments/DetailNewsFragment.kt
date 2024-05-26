package net.josesernamacia.powerpro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import net.josesernamacia.powerpro.databinding.FragmentDetailNewsBinding



class DetailNewsFragment : Fragment() {
    private var _binding : FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recuperar los datos del Bundle
        val args = arguments
        val title = args?.getString("title", "")
        val text = args?.getString("text", "")
        val image = args?.getString("image", "")

        // Actualizar la interfaz de usuario con los datos de la noticia
        binding.tvTituloNoticia.text = title
        binding.tvTextoNoticia.text = text
        // Cargar la imagen utilizando Glide u otra biblioteca similar
        Glide.with(requireContext()).load(image).into(binding.ivFotoNoticia)
    }


}