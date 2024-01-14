package com.jasmeet.memesharer.xml

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jasmeet.memesharer.databinding.ActivityMainBinding
import com.jasmeet.memesharer.retrofit.MemeApi
import com.jasmeet.memesharer.retrofit.RetrofitInstance
import com.jasmeet.memesharer.retrofit.response.MemeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var currentImageUrl: String? = null
    private lateinit var binding: ActivityMainBinding
    private var title:String? = null

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
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Checkout This Meme! $title : $currentImageUrl")
            val chooser = Intent.createChooser(intent, "Share this meme using...")
            startActivity(chooser)
        }
    }


    private fun loadMeme() {

        binding.progressBar.visibility = View.VISIBLE

        val call = RetrofitInstance.memeApi.getMeme()

        call.enqueue(object : Callback<MemeResponse> {
            override fun onResponse(call: Call<MemeResponse>, response: Response<MemeResponse>) {
                if (response.isSuccessful) {
                    currentImageUrl = response.body()?.url
                    title = response.body()?.title
                    binding.title.text = title
                    loadMemeImage()
                } else {
                    Toast.makeText(this@MainActivity, "Something Went Wrong !!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<MemeResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something Went Wrong !!", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun loadMemeImage(){
        Glide.with(this).load(currentImageUrl).listener(object : RequestListener<Drawable> {
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
    }


}