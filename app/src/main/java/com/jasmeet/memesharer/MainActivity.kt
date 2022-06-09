package com.jasmeet.memesharer

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jasmeet.memesharer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var currentImageUrl :String? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadMeme()
        binding.nextButton.setOnClickListener {
            loadMeme()
        }
        binding.shareButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type ="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Checkout This Meme! $currentImageUrl")
            val chooser = Intent.createChooser(intent,"Share this meme using...")
            startActivity(chooser)
        }
    }
     fun loadMeme(){
        binding.progressBar.visibility = View.VISIBLE

        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                currentImageUrl= response.getString("url")

                Glide.with(this).load(currentImageUrl).listener(object: RequestListener<Drawable>{

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }


                }).into(binding.memeImageView)
            },
            {
                Toast.makeText(this,"Something went Wrong!",Toast.LENGTH_SHORT).show()

            })

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }




}