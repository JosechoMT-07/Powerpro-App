package net.josesernamacia.powerpro.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.josesernamacia.powerpro.R

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder> () {

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

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_news, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemDescrp.text = descriptions[i]
        viewHolder.itemImage.setImageResource(images[i])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDescrp: TextView

        init {
            itemImage = itemView.findViewById(R.id.ivNewCard)
            itemTitle = itemView.findViewById(R.id.tv_title_card)
            itemDescrp = itemView.findViewById(R.id.tv_text_card)
        }
    }
}