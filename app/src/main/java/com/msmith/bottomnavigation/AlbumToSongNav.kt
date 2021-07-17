package com.msmith.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AlbumToSongNav : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       instance = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albumtosongnav, container, false)
    }

    //Simple companion object
    companion object{
        private var instance : AlbumToSongNav? = null
        fun getInstance() : AlbumToSongNav
        {
            return instance!!

        }
    }
}