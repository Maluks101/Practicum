package vcmsa.ci.practicum

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pg2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg2)

        val songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        val rating = intent.getIntegerArrayListExtra("rating") ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()
        val displayList = mutableListOf<String>()
        val btnAvg = findViewById<Button>(R.id.btnAvg)
        for (x in songs.indices) {
            if (rating[x] >= 5) {

                displayList.add("Song: ${songs[x]}, artist: ${artists[x]}, rating: ${rating[x]}, note: ${comments[x]}")
            }
            val listViewer = findViewById<ListView>(R.id.listViewer)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, displayList)
            listViewer.adapter = adapter
            btnAvg.setOnClickListener{
                    val numbers = arrayOf(rating)
                    val average = rating.average()
                    println("The average is: $average")


            }

            val backButton = findViewById<Button>(R.id.backButton)
            finish()



            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}