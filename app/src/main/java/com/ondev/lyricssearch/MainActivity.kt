package com.ondev.lyricssearch

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ondev.lyricssearch.data.repository.LyricsRepository
import com.ondev.lyricssearch.utils.Resource
import com.ondev.lyricssearch.utils.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apicall: LyricsRepository
    lateinit var text: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById<TextView>(R.id.lyric_txt)

        lifecycleScope.launch(Dispatchers.IO) {
            Log.d(TAG, "onCreate: Calling...")
            val result = apicall.searchLyrics("Mana", "Labios Compartidos")
            lifecycleScope.launch(Dispatchers.Main) {
                when (result.status) {
                    Resource.Status.SUCCESS -> {
                        Log.d(TAG, "onCreate: RESULT: ${result!!.data!!.lyrics}")
                        text.text = result.data!!.lyrics
                    }
                    Resource.Status.ERROR -> {
                        Log.d(TAG, "onCreate: ERROR: ${result.message}")

                    }
                    Resource.Status.LOADING -> {
                        text.text = "Cargando..."
                        Log.d(TAG, "onCreate: Cargando...")
                    }
                }
            }
        }
    }
}