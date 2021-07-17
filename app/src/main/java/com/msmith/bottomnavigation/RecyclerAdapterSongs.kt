package com.msmith.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterSongs: RecyclerView.Adapter<RecyclerAdapterSongs.ViewHolder> {

/* HARD CODED VALUES USED FOR PRACTICE BEFORE FILE READ
        private var album1songs = arrayOf("A1S1", "A1S2", "A1S3")
        private var album2songs = arrayOf("A2S1", "A2S2", "A2S3")
        private var album3songs = arrayOf("A3S1", "A3S2", "A3S3")

        private var titles = arrayOf("Song1", "Song2", "Song3")
        private var songInfo = arrayOf("MORE INFO 1", "MORE INFO 2", "MORE INFO 3")
*/

        private var pos : Int = -1
        private var songs = arrayOf<ArrayList<Song>?>()


        constructor(pos : Int) : this()
        {
            this.pos = pos
        }

    constructor()

    init
        {
            // For each album in the hashmap, it will grab the arraylist of songs in the hashmap and add them to the potential songs list
            // that is here, and so for every pos that is in the album recycler view, there will be another list of songs that will be displayed


            for(i in BeatlesAlbums.indices)
            {
                songs += songsLists[BeatlesAlbums[i].getName()]
            }
        }
        public fun setPos(pos : Int)
        {
            this.pos = pos
        }
        override fun onBindViewHolder(holder: RecyclerAdapterSongs.ViewHolder, position: Int)
        {
            holder.itemSongTitle.text = songs[pos]!![position]!!.getName()!!
            holder.itemSongsDetail.text = songs[pos]!![position]!!.getDetail()
        }
        override fun getItemCount(): Int
        {
            return songs[pos]!!.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterSongs.ViewHolder
        {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view_for_songs, parent, false)
            return ViewHolder(v)
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        {
            var itemSongTitle: TextView
            var itemSongsDetail: TextView
            init
            {
                itemSongTitle = itemView.findViewById(R.id.songHeading)
                itemSongsDetail = itemView.findViewById(R.id.song)

                var handler = Handler()
                itemView.setOnClickListener(handler)
            }

            inner class Handler() : View.OnClickListener
            {
                override fun onClick(v: View?) {
                    val itemPosition  = layoutPosition

//pos is for the selected album, the itemposition is the position of the selected song, and the get name is the string that allows for the passing of the bundle to the api

                    println("song selected position:  $itemPosition : song is " + songs[pos]!![itemPosition]!!.getName()!!)

                    //Gets the button that was pressed.
                    var navController = Navigation.findNavController(SongFragment.getInstance().requireView())
                    val bundle = Bundle()
                    bundle.putInt("position", itemPosition)
                    bundle.putString("selected", songs[pos]!![itemPosition]!!.getName())
                    navController.navigate(R.id.song_to_youtube,bundle)
                }
            }






        }
    }






