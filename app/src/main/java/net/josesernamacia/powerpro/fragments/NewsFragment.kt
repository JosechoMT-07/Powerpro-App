package net.josesernamacia.powerpro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.adapters.NewsAdapter
import net.josesernamacia.powerpro.databinding.FragmentNewsBinding


class NewsFragment : Fragment() {
    private var _binding : FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater,container,false)


        binding.rvNoticias.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNoticias.adapter = NewsAdapter() // Aquí puedes pasar los datos necesarios al adaptador si es necesario

        return binding.root
    }



}