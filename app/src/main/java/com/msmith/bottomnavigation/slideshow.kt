package com.msmith.bottomnavigation

import android.widget.ImageView
import android.widget.TextView
import java.util.LinkedHashSet
import kotlin.random.Random

//Pulled directly from SlideShow as it will have the same effect
private var noSlides = 0
private var duration: Long = 0
private var count: Int = 0
private var imageView: ImageView? = null

class slideshow : Thread
{



constructor()
{
    count = 1
    duration = 3
    noSlides = 16
    imageView = MainActivity.getInstance().findViewById(R.id.slideshowImage)

}



override public fun run()
{
    var count = 0




    //choose which files to show

    // use a set so that there are no repeating indices for the arrays, set for 0-9 for a total of ten selections


    while(true) {
        for (I in BeatlesAlbums.indices) {

            //set the handler with the image of the album
            var handler = slideshowHandler(BeatlesAlbums[I].getPic(), duration)
            MainActivity.getInstance().runOnUiThread(handler) //Activate the handler on the Ui thread
            Thread.sleep(duration * 1000)   //Sleep the designated time
            count++

        }
    }

}
class slideshowHandler : Runnable
{
    private var fn: String = ""

    private var duration : Long
    constructor(fn: String, duration: Long)
    {
        this.fn = fn

        this.duration = duration
    }
    override fun run()
    {


        //sets the variables imageView and textView to match those established in the mainactivity xml
        var imageView = MainActivity.getInstance().findViewById<ImageView>(R.id.slideshowImage)

        //Change the caption to the passed in caption from the constructor

        Thread.sleep((1000 * duration).toLong())   //Wait the 5 seconds

        if(imageView != null) {
            var context = imageView.getContext()
            var id = MainActivity.getInstance().resources.getIdentifier(
                fn,
                "drawable",
                context.packageName
            )
            //More Universal, doesn't base on the hardcoded values input
            // finding the id and setting Image Resource based upon it is much easier and universal
            imageView.setImageResource(id)
        }
    }
}
}