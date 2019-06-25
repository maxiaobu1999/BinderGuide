package com.norman.binderguide

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.norman.aidl.AIDLActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_aidl)
            .setOnClickListener(View.OnClickListener { v ->
                startActivity(Intent(this@MainActivity, AIDLActivity::class.java))
            })
    }
}
