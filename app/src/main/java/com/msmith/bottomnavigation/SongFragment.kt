package com.msmith.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_song.*

class SongFragment : Fragment() { // TODO: Rename and change types of parameters


    private var titles = arrayOf("ALBUM1", "ALBUM2", "ALBUM3")

    private var layoutManager: RecyclerView.LayoutManager? = null;
    private var adapter: RecyclerView.Adapter<RecyclerAdapterSongs.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_song, container, false)
    }

    //Simple companion object
    companion object{
        private var instance :SongFragment? = null
        fun getInstance() :SongFragment
        {
            return instance!!

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var arguments = this.arguments
        var pos = arguments?.getInt("position")

        //all the glue that ties it to the adapter
        layoutManager = LinearLayoutManager(MainActivity.getInstance())
        SongRecyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapterSongs(pos!!)
        SongRecyclerView.adapter = adapter

    }
}