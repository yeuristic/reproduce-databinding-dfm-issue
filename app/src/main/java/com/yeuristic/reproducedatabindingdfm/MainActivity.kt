package com.yeuristic.reproducedatabindingdfm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.button)
        initListener()
    }

    private fun initListener() {
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            button -> startActivity(Intent().setClassName(BuildConfig.APPLICATION_ID, "com.yeuristic.dynamicfeature.DynamicActivity"))
        }
    }
}