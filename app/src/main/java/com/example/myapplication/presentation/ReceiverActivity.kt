package com.example.myapplication.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityReceiverBinding
import com.example.myapplication.domain.model.Posts

class ReceiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedData: Posts? = intent.getParcelableExtra("objPost")
        binding.tvId.text = receivedData!!.id.toString()
        binding.tvUserId.text = "UserId - "+receivedData!!.userId.toString()
        binding.tvTitle.text = receivedData!!.title.toString()
        binding.tvDec.text = receivedData!!.body.toString()

    }


}