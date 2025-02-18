package com.example.apptienda

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apptienda.databinding.ActivitySecondBinding

class SecondActivity {
    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivitySecondBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivitySecondBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }
    }
}