package net.josesernamacia.powerpro.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.adapters.NewsAdapter
import net.josesernamacia.powerpro.databinding.FragmentNewsBinding
import net.josesernamacia.powerpro.model.News


class NewsFragment : Fragment(), NewsAdapter.NewsAdapterClickInterface  {
    private var _binding : FragmentNewsBinding? = null
    private val binding get() = _binding!!

    //=====
    private val DATABASE = "news"
    private lateinit var adapter: NewsAdapter
    private lateinit var newsList: MutableList<News>
    private lateinit var databaseRef: CollectionReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getDataFromFirebase()
    }

    private fun init() {
        databaseRef = FirebaseFirestore.getInstance().collection(DATABASE)

        binding.rvNoticias.setHasFixedSize(true)
        binding.rvNoticias.layoutManager = LinearLayoutManager(context)

        newsList = mutableListOf()
        adapter = NewsAdapter(newsList)
        adapter.setListener(this)
        binding.rvNoticias.adapter = adapter
    }

    private fun getDataFromFirebase() {
        databaseRef.get().addOnSuccessListener {
            newsList.clear()
            val listNews = it.documents.map { document ->
                News(
                    document.getString("title")?:"",
                    document.getString("text")?:"",
                    document.getString("image")?:"")
            }
            newsList.addAll(listNews)
            adapter.notifyDataSetChanged()
        }.addOnFailureListener {
            Toast.makeText(context, "Error al cargar la lista de noticias", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClickedNew(news: News) {
        val detailFragment = DetailNewsFragment()

        // Crear un Bundle para pasar datos al fragmento de detalles
        val args = Bundle()
        args.putString("title", news.title)
        args.putString("text", news.text)
        args.putString("image", news.image)

        // Establecer los argumentos en el fragmento de detalles
        detailFragment.arguments = args

        // Reemplazar el fragmento actual con el fragmento de detalles
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rvNoticias, detailFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}