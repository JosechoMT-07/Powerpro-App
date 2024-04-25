package net.josesernamacia.powerpro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.josesernamacia.powerpro.adapters.NewsAdapter


class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.rvNoticias)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = NewsAdapter() // Aquí puedes pasar los datos necesarios al adaptador si es necesario

        return view
    }



}