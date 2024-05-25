package net.josesernamacia.powerpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.josesernamacia.powerpro.R
import net.josesernamacia.powerpro.model.News

class NewsAdapter(): RecyclerView.Adapter<NewsAdapter.MyViewHolder> () {
//class NewsAdapter(private val newsList : ArrayList<News>): RecyclerView.Adapter<NewsAdapter.MyViewHolder> () {

    val titles = arrayOf("Se quema cuadro","Nuevas herramientas","Nueva normativa","Nuevo coche","Ejemplo1","Ejemplo2","Ejemplo3")

    val descriptions = arrayOf( "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.",
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque vel imperdiet enim. Donec a quam eget dolor faucibus vestibulum a sed er vehicula.")

    val images = intArrayOf(R.drawable.ic_launcher_foreground,
                            R.drawable.ic_launcher_foreground,
                            R.drawable.ic_launcher_foreground,
                            R.drawable.ic_launcher_foreground,
                            R.drawable.ic_launcher_foreground,
                            R.drawable.ic_launcher_foreground,
                            R.drawable.ic_launcher_foreground)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_news, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {

        //descomentar para ver si funciona
        //val news : News = newsList[i]

        //descomentar los dos primeros y dejar comentado el de la imagen
        //viewHolder.itemTitle.text = news.title
        //viewHolder.itemText.text = news.text
        //viewHolder.itemImage.drawable = news.imageURL

        //comentar forma actual que funciona
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDescrp.text = descriptions[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {

        //return newsList.size
        return titles.size
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        //descomentar los 3 primeros y comentar los 3 var  y el init
        //val itemTitle : TextView = itemView.findViewById(R.id.tvTitleCard)
        //val itemText : TextView = itemView.findViewById(R.id.tvTextCard)
        //val itemImage : ImageView = itemView.findViewById(R.id.ivNewCard)
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDescrp: TextView

        init {
            itemImage = itemView.findViewById(R.id.ivNewCard)
            itemTitle = itemView.findViewById(R.id.tvTitleCard)
            itemDescrp = itemView.findViewById(R.id.tvTextCard)
        }
    }
}