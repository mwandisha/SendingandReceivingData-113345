package com.meanzunlimitedinc.sendingreceivingdata

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var repliedMessageView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set the title for the action bar
        supportActionBar?.title = "Sending Data - Main"

        // Initialize views
        editText = findViewById(R.id.editTextMain)
        val sendButton: Button = findViewById(R.id.main_send_button)
        repliedMessageView = findViewById(R.id.repliedMessage)

        // Set click listener for the send button
        sendButton.setOnClickListener {
            val message = editText.text.toString()
            // Create an intent with URI
            val messageUri = Uri.parse("message:$message")
            val intent = Intent(this, SecondActivity::class.java).apply {
                data = messageUri // Pass URI as intent data
            }
            startActivity(intent)
        }
    }

    // Receiving reply from SecondActivity using intent extras
    override fun onResume() {
        super.onResume()
        intent?.let {
            if (it.hasExtra("reply")) {
                val reply = it.getStringExtra("reply")
                repliedMessageView.text = "Reply: $reply" // Display reply
            }
        }
    }
}
