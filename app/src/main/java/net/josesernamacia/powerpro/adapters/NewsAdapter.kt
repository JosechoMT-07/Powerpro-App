package net.josesernamacia.powerpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.databinding.ItemNewsBinding
import net.josesernamacia.powerpro.model.News

class NewsAdapter(private val news: MutableList<News>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder> () {

    private lateinit var storageRef: StorageReference

    private var listener: NewsAdapterClickInterface? = null
    fun setListener(listener: NewsAdapterClickInterface) {
        this.listener = listener
    }

    class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        init()
        return NewsViewHolder(binding)
    }

    private fun init() {
        storageRef = FirebaseStorage.getInstance().getReference()
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(news[position]) {
                binding.tvTitleCard.text = this.title
                binding.tvTextCard.text = this.text
                val imageRef = storageRef.child("noticias").child(this.image)
                Glide.with(binding.cvItem.context)
                    .load(imageRef)
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.ivNewCard)
                binding.cvItem.setOnClickListener {
                    listener?.onClickedNew(this)
                }
            }
        }
    }

    interface NewsAdapterClickInterface {
        fun onClickedNew(newsData: News)
    }

}