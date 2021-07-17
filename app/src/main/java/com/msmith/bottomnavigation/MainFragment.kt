package com.msmith.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

var isRunning = false
class MainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this


        //ADD CODE HERE
        var slideshowofalbums = slideshow()

        slideshowofalbums.start();
        isRunning = true;

        while(!isRunning)
        {
            slideshowofalbums.interrupt()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    //Simple companion object
    companion object{
        private var instance : MainFragment? = null
        fun getInstance() : MainFragment
        {
            return instance!!

        }
    }
}