package com.example.coroutinestart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.coroutinestart.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            lifecycleScope.launch {
                load()
            }
        }
    }

    private suspend fun load() {
        binding.progress.isVisible = true
        binding.buttonLoad.isEnabled = false
        val tt = loadCity()
        binding.tvCityCount.text = tt

        val test = loadTemperature(tt)

        binding.tvTemCount.text = test.toString()
        binding.progress.isVisible = false
        binding.buttonLoad.isEnabled = true
    }

    private suspend fun loadCity(): String {
        delay(5000)

        return "Tokyo"
    }

    private suspend fun loadTemperature(city: String): Int {

        Toast.makeText(this, "Temperature in $city 15 ", Toast.LENGTH_SHORT).show()

        delay(5000)

        return 15
    }

}
