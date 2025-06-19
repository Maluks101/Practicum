package vcmsa.ci.practicum

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val songs = mutableListOf<String>()
    private val names = mutableListOf<String>()
    private val ratings = mutableListOf<Int>()
    private val comments = mutableListOf<String>()
    private lateinit var songName: EditText
    private lateinit var artistName: EditText
    private lateinit var userRating: EditText
    private lateinit var userComments: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        songName = findViewById(R.id.songName)
        artistName = findViewById(R.id.artistName)
        userRating = findViewById(R.id.userRating)
        userComments = findViewById(R.id.userComments)
        val exitButton = findViewById<Button>(R.id.exitButton)
        val addPlaylist = findViewById<Button>(R.id.addPlaylist)
        val listViewButton = findViewById<Button>(R.id.listViewButton)

        addPlaylist.setOnClickListener {
            try {
                val name = songName.text.toString()
                val artist = artistName.text.toString()
                val rating = userRating.text.toString().toInt()
                val comment = userComments.text.toString()
                if (name.isNotEmpty() && artist.isNotEmpty()) {
                    songs.add(name)
                    names.add(artist)
                    ratings.add(rating)
                    comments.add(comment)
                    Log.d("MainActivity", "Playlist created: $name, $artist, $rating, $comment")
                    Toast.makeText(this, "Playlist created", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Please fill in everything", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid rating", Toast.LENGTH_SHORT).show()
                Log.e("MainActivity", "Rating error", e)
            }

        }
        listViewButton.setOnClickListener {
            val intent = Intent(this, pg2::class.java)
            intent.putStringArrayListExtra("songs", ArrayList(songs))
            intent.putStringArrayListExtra("artists", ArrayList(names))
            intent.putIntegerArrayListExtra("ratings", ArrayList(ratings))
            intent.putStringArrayListExtra("comments", ArrayList(comments))

        }
        exitButton.setOnClickListener {
            finish()





            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}