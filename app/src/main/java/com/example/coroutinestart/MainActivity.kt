package com.example.coroutinestart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.coroutinestart.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
//    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            load()
        }
    }
    private fun load() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
//        val tt = loadCity()
//        binding.tvCityCount.text = tt
        loadCity {
            binding.tvCityCount.text = it
//            val test = loadTemperature(it)
            loadTemperature(it) {
                binding.tvTemCount.text = it.toString()
                binding.progress.isVisible = false
                binding.buttonLoad.isEnabled = true
            }
//            binding.tvTemCount.text = test.toString()
//            binding.progress.isVisible = false
//            binding.buttonLoad.isEnabled = true
        }

//val test = loadTemperature(tt)
//        binding.tvTemCount.text = test.toString()
//        binding.progress.isVisible = false
//        binding.buttonLoad.isEnabled = true
    }

    private fun loadCity(callback: (String) -> Unit) {
      thread {
            Thread.sleep(5000)
          runOnUiThread{
//          Handler(Looper.getMainLooper()).post{
//            handler.post {
                callback.invoke("Tokyo")
            }
        }
//        Thread.sleep(5000)
//        return
    }

    private fun loadTemperature(city: String, callback: (Int) -> Unit) {

        thread {
            runOnUiThread{
//            Handler(Looper.getMainLooper()).post{
//            handler.post {
                Toast.makeText(this, "Temperature in $city 15 ", Toast.LENGTH_SHORT).show()
            }
            Thread.sleep(5000)
            runOnUiThread {
//            Handler(Looper.getMainLooper()).post{
//                handler.post {
                    callback.invoke(15)
                }
            }

        }
//        Toast.makeText(this,"Temperature in $city 15 ",Toast.LENGTH_SHORT).show()
//        Thread.sleep(5000)
//        return  15
    }
