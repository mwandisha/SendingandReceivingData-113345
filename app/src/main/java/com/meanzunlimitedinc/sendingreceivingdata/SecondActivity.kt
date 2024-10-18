package com.meanzunlimitedinc.sendingreceivingdata

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Set the title for the action bar
        supportActionBar?.title = "Sending Data - Second"

        // Initialize views
        val receivedMessageView: TextView = findViewById(R.id.receivedMessage)
        editText = findViewById(R.id.editTextSecond)
        val sendButton: Button = findViewById(R.id.sendButtonSecond)

        // Get the URI from the intent
        intent?.data?.let { data: Uri ->
            val message = data.schemeSpecificPart // Extract the message
            receivedMessageView.text = "Received: $message"
        }

        // Set click listener for the send button to reply back
        sendButton.setOnClickListener {
            val replyMessage = editText.text.toString()
            // Sending the data back to MainActivity using intent extras
            val replyIntent = Intent(this, MainActivity::class.java).apply {
                // Using intent extras to send data
                putExtra("reply", replyMessage)
            }
            startActivity(replyIntent)
        }
    }
}
