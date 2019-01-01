package com.mobile.clairecannatti.andwallet

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.mobile.clairecannatti.andwallet.adapter.BankingAdapter
import kotlinx.android.synthetic.main.summary_layout.*

class SummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.summary_layout)

        if (intent.hasExtra("INCOMES")) {
            tvIncome.text = intent.getStringExtra(MainActivity.INCOMES)
        }
        if (intent.hasExtra("EXPENSES")) {
            tvExpenses.text = intent.getStringExtra(MainActivity.EXPENSES)
        }
        if (intent.hasExtra("BALANCE")) {
            tvBalance.text = intent.getStringExtra(MainActivity.BALANCE)
        }

    }


}