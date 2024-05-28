package net.josesernamacia.powerpro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.databinding.FragmentDetailNewsBinding
import net.josesernamacia.powerpro.model.News


class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding
    private var newsData: News? = null
    private lateinit var storageRef: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    companion object{
        const val TAG = "DetailNewsFragment"

        @JvmStatic
        fun newInstance(title: String, text: String, image: String) =
            DetailNewsFragment().apply {
                arguments = Bundle().apply {
                    putString("title", title)
                    putString("text", text)
                    putString("image", image)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            newsData = News(
                arguments?.getString("title").toString(),
                arguments?.getString("text").toString(),
                arguments?.getString("image").toString()
            )

            binding.tvTituloNoticia.setText(newsData?.title)
            binding.tvTextoNoticia.setText(newsData?.text)


            val imageRef = storageRef.child("noticias").child(newsData!!.image)
            Glide.with(binding.fragmentDetailNews.context)
                .load(imageRef)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivFotoNoticia)
        }
    }

    private fun init() {
        storageRef = FirebaseStorage.getInstance().getReference()
    }


}