package net.josesernamacia.powerpro.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.adapters.NewsAdapter
import net.josesernamacia.powerpro.databinding.FragmentNewsBinding
import net.josesernamacia.powerpro.model.News


class NewsFragment : Fragment() {
    private var _binding : FragmentNewsBinding? = null
    private val binding get() = _binding!!


    private  lateinit var recyclerView: RecyclerView
    private lateinit var newsArrayList: ArrayList<News>
    private lateinit var myAdapter: NewsAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater,container,false)
        //newsArrayList = arrayListOf()


        binding.rvNoticias.layoutManager = LinearLayoutManager(requireContext())

        binding.rvNoticias.adapter = NewsAdapter()
        //binding.rvNoticias.adapter = NewsAdapter(newsArrayList)
        //myAdapter = NewsAdapter(newsArrayList)

        //EventChangeListener()
        return binding.root
    }




    private fun EventChangeListener(){
        db = FirebaseFirestore.getInstance()
        db.collection("news").
                addSnapshotListener(object : EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null){
                            Log.e("Firestore Error",error.message.toString())
                            return
                        }

                        for (dc : DocumentChange in value?.documentChanges!!){
                            if (dc.type == DocumentChange.Type.ADDED){
                                newsArrayList.add(dc.document.toObject(News::class.java))
                            }
                        }

                        myAdapter.notifyDataSetChanged()
                    }

                })
    }

}