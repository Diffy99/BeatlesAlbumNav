package com.msmith.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

var BeatlesAlbums : ArrayList<Albums> = ArrayList()
var songsLists: HashMap<String, ArrayList<Song>> = HashMap()

class MainActivity : AppCompatActivity() {

    companion object {
        private var instance: MainActivity? = null
        public fun getInstance(): MainActivity {
            return instance!!
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this
        supportActionBar?.hide()

        val navController = Navigation.findNavController(this, R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

        var inputStreamForAlbums = this.assets.open("albums.txt")
        var reader1 = BufferedReader(InputStreamReader(inputStreamForAlbums))
        var lines1 = reader1.readLines()
        var arrayLines = lines1.toTypedArray()
        var allData1 = arrayOf<Array<String>>()

        for (i in arrayLines.indices) {

            var array1 = arrayLines[i].split("^")
            allData1 += array1.toTypedArray()
            var album = Albums(array1[0], array1[2], array1[3])

            BeatlesAlbums.add(album)


        }

        reader1.close()


        //Here we put the songs in the hashmap with the key being the album name


        for (i in BeatlesAlbums.indices) {
            var inputStreamForSongs =
                this.assets.open(BeatlesAlbums[i].getPic() + ".txt") // since the pic name is equal to the asset name

            var reader1 = BufferedReader(InputStreamReader(inputStreamForSongs))
            var lines1 = reader1.readLines()
            var arrayLines = lines1.toTypedArray()
            var allData1 = arrayOf<Array<String>>()
            var arrayOfSongs: ArrayList<Song> = ArrayList()

            for (i in arrayLines.indices) // for song in the file, it will add to an array list of songs that will be in a hashmap later accessed by the recycler view
            {

                var array1 = arrayLines[i].split("^")
                allData1 += array1.toTypedArray()
                var song = Song(array1[0], array1[1])
                arrayOfSongs.add(song)


            }
            songsLists.put(
                BeatlesAlbums[i].getName(),
                arrayOfSongs
            )
            // now for example, there is a key of please please me, that has all of the songs from
            // from the please please me album in an array list.


        }
        /*println("LIST OF THE SONGS PER ALBUM BELOW________________________________________")
        for (i in BeatlesAlbums.indices) {
            println(
                BeatlesAlbums[i].getName() + "=======\n" + songsLists.get(BeatlesAlbums[i].getName()).toString()

            )


        }*/


    }
}
