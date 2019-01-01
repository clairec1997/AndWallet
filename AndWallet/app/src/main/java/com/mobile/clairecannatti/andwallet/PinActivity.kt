package com.mobile.clairecannatti.andwallet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        btnGo.setOnClickListener{
            var intentStart = Intent()
            intentStart.setClass(PinActivity@this, MainActivity::class.java)

            if(etPin.text.toString() == "1234")  startActivity(intentStart)
        }
    }
}
