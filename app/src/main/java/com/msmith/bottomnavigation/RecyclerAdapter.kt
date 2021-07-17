package com.msmith.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import java.io.*

@Suppress("JoinDeclarationAndAssignment")
class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    /*
    //hard coded testing below with the pictures and album names and made up details
    private var BeatlesAlbums = arrayOf(Albums("abbeyroad", "some date here1", "abbeyroad"), Albums("beatlesforsale", "some date here2", "beatlesforsale"), Albums("harddaysnight", "some date3", "harddaysnight"))
    */


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init
        {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.AlbumName)
            itemDetail = itemView.findViewById(R.id.AlbumInfo)

            var handler = Handler()
            itemView.setOnClickListener(handler)
        }
        inner class Handler() : View.OnClickListener
        {
            override fun onClick(v: View?) {
                val itemPosition  = layoutPosition

                //Gets the button that was pressed.
                var navController = Navigation.findNavController(AlbumFragment.getInstance().requireView())
                val bundle = Bundle()
                bundle.putInt("position", itemPosition)
                navController.navigate(R.id.album_to_songs,bundle)
            }
        }





    }

    override fun getItemCount(): Int {
        return BeatlesAlbums!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = BeatlesAlbums!!.get(position).getName()
        holder.itemDetail.text = BeatlesAlbums!!.get(position).getDetail()
        holder.itemImage.setImageResource(MainActivity.getInstance().resources.getIdentifier(BeatlesAlbums!![position].getPic(), "drawable", MainActivity.getInstance().packageName))


    }

}