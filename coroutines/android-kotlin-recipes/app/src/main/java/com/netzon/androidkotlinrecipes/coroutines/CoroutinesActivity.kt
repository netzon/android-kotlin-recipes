package com.netzon.androidkotlinrecipes.coroutines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.netzon.androidkotlinrecipes.R
import kotlinx.android.synthetic.main.activity_coroutines.*

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        this.title = "Coroutines"

        btn_suspend1.setOnClickListener {
            val intent = Intent(this, Suspend1Activity::class.java)
            startActivity(intent)
        }

        btn_suspend2.setOnClickListener {
            val intent = Intent(this, Suspend2Activity::class.java)
            startActivity(intent)
        }

        btn_suspend3.setOnClickListener {
            val intent = Intent(this, Suspend3Activity::class.java)
            startActivity(intent)
        }
    }
}
