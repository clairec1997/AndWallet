package com.mobile.clairecannatti.andwallet

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.helper.ItemTouchHelper
import com.mobile.clairecannatti.andwallet.adapter.BankingAdapter
import com.mobile.clairecannatti.andwallet.data.Expense
import com.mobile.clairecannatti.andwallet.touch.BankingTouchHelperCallback
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val bankAdapter = BankingAdapter(this)

    companion object {
        val INCOMES = "INCOMES"
        val EXPENSES = "EXPENSES"
        val BALANCE = "BALANCE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cbIncome.setOnCheckedChangeListener{_, isChecked -> bankAdapter.isIncome = isChecked}

        btnSave.setOnClickListener {
            bankAdapter.isIncome = cbIncome.isChecked

            if (etAmt.text.isEmpty()){
                etAmt.error = "Cannot be empty"
            } else if (etLabel.text.isEmpty()) {
                etLabel.error = "Cannot be empty"
            } else{

                if (cbIncome.isChecked){
                    val transaction = Expense(etLabel.text.toString(), etAmt.text.toString().toInt(), true)
                    bankAdapter.addTransaction(transaction)
                } else {
                    val transaction = Expense(etLabel.text.toString(), etAmt.text.toString().toInt(), false)
                    bankAdapter.addTransaction(transaction)
                }
            }

            bankAdapter.calculateBalance()
            tvBalance.text = bankAdapter.balance.toString()

        }

        btnSummary.setOnClickListener {
            val intentStart = Intent()
            intentStart.setClass(MainActivity@this, SummaryActivity::class.java)

            val incomes = bankAdapter.totalIncome().toString()
            val expenses = bankAdapter.totalExpenses().toString()
            bankAdapter.calculateBalance()

            intentStart.putExtra(INCOMES, incomes)
            intentStart.putExtra(EXPENSES, expenses)
            intentStart.putExtra(BALANCE, bankAdapter.balance.toString())

            startActivity(intentStart)

           // startActivityForResult(intentStart, REQUEST_DETAILS)
        }

        recycler.adapter = bankAdapter

        val callback = BankingTouchHelperCallback(bankAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recycler)

        btnDeleteAll.setOnClickListener {
            val size = bankAdapter.itemCount - 1
            for (i in 0..size){
                bankAdapter.deleteTransaction(0)
            }

            bankAdapter.calculateBalance()
            tvBalance.text = bankAdapter.balance.toString()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_scrolling, menu)
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

}
