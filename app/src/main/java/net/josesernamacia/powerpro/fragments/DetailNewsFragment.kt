package net.josesernamacia.powerpro.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import net.josesernamacia.powerpro.databinding.FragmentDetailNewsBinding
import net.josesernamacia.powerpro.model.News


class DetailNewsFragment : Fragment() {
    private lateinit var binding: FragmentDetailNewsBinding
    private var newsData: News? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
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


            // Cargar la imagen utilizando Glide
            Glide.with(this)
                .load(newsData?.image)
                .into(binding.ivFotoNoticia)

        }
    }


}