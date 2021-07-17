package com.msmith.bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import org.json.JSONObject
import java.net.URL
import kotlinx.android.synthetic.main.fragment_main.*

class UIThreadHelper: Thread
{
    private var video: String = ""

    constructor(url: String)
    {
        this.video = url
    }

    @Suppress("UsePropertyAccessSyntax")
    public override fun run()
    {
        // update the webview
//Update the webView
        var web = MainActivity.getInstance().findViewById<WebView>(R.id.webView)
        val settings = web.getSettings()
        settings.setJavaScriptEnabled(true)
        settings.setDomStorageEnabled(true)
        settings.setMinimumFontSize(10)
        settings.setLoadWithOverviewMode(true)
        settings.setUseWideViewPort(true)
        settings.setBuiltInZoomControls(true)
        settings.setDisplayZoomControls(false)
        web.setVerticalScrollBarEnabled(false)
        settings.setDomStorageEnabled(true)
        web.setWebViewClient(WebViewClient())
        var str = "https://www.youtube.com/watch?v=$video"
        web.loadUrl(str)



        println("____________________________HERE IS WHERE THE MAGIC HAPPENS?_______________________________________")
        println("Here is the url: \n" + video.toString() + "\n==================================")
    }
}


class Helper : Runnable
{
    private var song : String = ""
    private var artist : String = ""
    private var url : String  = ""

    constructor(url : String, artist : String, song : String)
    {
        this.song = song
        this.artist = artist
        this.url = url
    }


    override public fun run()
    {
        val data = URL(url).readText()  //Read all the data at this URL
        println(data)

        var json = JSONObject(data)

       println(json)

        var items = json.getJSONArray("items") //this is the items corresponding to 50 results with all their info
        //^^ Array of hashmaps ^^//

        var titles = ArrayList<String>();
        var videos = ArrayList<String>();

        for(i in 0 until items.length())
        {
            var videoObject = items.getJSONObject(i)
            //val title = videoObject.getString("title");
            //val videoId = videoObject.getString("id");
            //println(videoObject)
            var idDict = videoObject.getJSONObject("id")
            println(idDict)
            var videoId = idDict.getString("videoId")
            println(videoId)
            var snippetDict = videoObject.getJSONObject("snippet")
            var title = snippetDict.getString("title")
            println(title)
            titles.add(title)
            videos.add(videoId)

        }

        var selectedVideo : String = ""
        var selectedTitle : String = ""
        selectedTitle = titles[0]
        selectedVideo = videos[0]

        // compare the array of titles with the original song
        //the og song doesn't have the +, but the space so keep that in mind
        //iterate through each title,
        //make sure the artist name is in the title
        //make sure the song title is also in the title
        //indexOf(artist)
        //indexOf(song)
        // make sure both are not -1

        //for best match, since it is sorted by the number of hits in the api, the first one that would have
        // both the name of the song and the band name "the Beatles" would be the best match per the assignment details.
        // here I will test each and every title, for one that contains both as a substring.

        for(i in videos.indices)
        {
            println(song)
            if((titles[i].contains("The Beatles") && titles[i].contains(song)))
            {
                selectedTitle = titles[i]
                selectedVideo = videos[i]
                break;
            }
        }

        var helper1 = UIThreadHelper(selectedVideo)
        MainActivity.getInstance().runOnUiThread(helper1)


    }
}

class YoutubeFragment : Fragment() {

    //Simple companion object
    companion object {
        private var instance: YoutubeFragment? = null
        fun getInstance(): YoutubeFragment {
            return instance!!

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance = this





             var song = requireArguments().getString("selected")
             var origSong = song //requireArguments().getString("selected")

        song = song!!.replace(" ", "+")

        //Set the artist
        var artist = "The Beatles"
        artist = artist.replace(" ","+")
        var origArtist = "The Beatles"

        //Encode search for YouTube
        val keywords = "$artist+$song"
        val max = 50

        val string = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=$keywords&order=viewCount&maxResults=$max&type=video&videoCategory=Music&key=AIzaSyBjfflfiUvfEGiYBgoHw2nVsU4GFyQbdoo"

        var helper = Helper(string,origArtist ,origSong!! )
        var thread = Thread(helper)
        thread.start()

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }
}