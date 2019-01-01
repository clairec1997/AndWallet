package com.mobile.clairecannatti.andwallet.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.mobile.clairecannatti.andwallet.R
import com.mobile.clairecannatti.andwallet.R.id.cbIncome
//import com.mobile.clairecannatti.andwallet.R.id.cbIncome
import com.mobile.clairecannatti.andwallet.data.Expense
import com.mobile.clairecannatti.andwallet.touch.BankingTouchHelperAdapter
import kotlinx.android.synthetic.main.expense_layout.view.*
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*

class BankingAdapter(val context: Context) : RecyclerView.Adapter<BankingAdapter.ViewHolder>(),
        BankingTouchHelperAdapter {

    var isIncome: Boolean = false
    var balance = 0

    val transactions = mutableListOf<Expense>(
//            Expense("Lunch", 6, false),
//            Expense("Babysitting", 35, true)
    )

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) : ViewHolder {
        if (!isIncome) {
            val view = LayoutInflater.from(context).inflate(
                    R.layout.income_layout, parent, false)
            return ViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(
                    R.layout.expense_layout, parent, false)
            return ViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense = transactions[position]

        holder.tvAmt.text = expense.amount.toString()
        holder.tvLabel.text = expense.moneyLabel
       // holder.isIncome.isChecked = expense.income
    }

    fun deleteTransaction(adapterPosition: Int) {
        transactions.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
    }

    fun addTransaction(expense: Expense) {
        transactions.add(0, expense)
        notifyItemInserted(0)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAmt = itemView.tvAmt
        val tvLabel = itemView.tvLabel
        var isIncome = itemView.cbIncome
    }

    override fun onDismissed(position: Int) {
        deleteTransaction(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(transactions, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun calculateBalance(){
        balance = 0
        val size = itemCount-1
        for (i in 0..size){
            if (transactions[i].income) balance += transactions[i].amount
            else balance -= transactions[i].amount
        }
    }

    fun totalExpenses(): Int {
        var expenses = 0
        val size = itemCount-1
        for (i in 0..size){
            if (!transactions[i].income) expenses += transactions[i].amount
        }
        return expenses
    }

    fun totalIncome(): Int {
        var income = 0
        val size = itemCount-1
        for (i in 0..size){
            if (transactions[i].income) income += transactions[i].amount
        }
        return income
    }
}