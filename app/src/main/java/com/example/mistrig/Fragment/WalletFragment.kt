package com.example.mistrig.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mistrig.Adapters.Transaction
import com.example.mistrig.Adapters.TransactionAdapter
import com.example.mistrig.R

class WalletFragment : Fragment() {

    private lateinit var walletBalanceTextView: TextView
    private lateinit var addFundsButton: Button
    private lateinit var withdrawFundsButton: Button
    private lateinit var transactionHistoryRecyclerView: RecyclerView
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wallet, container, false)

        walletBalanceTextView = view.findViewById(R.id.wallet_balance)
        addFundsButton = view.findViewById(R.id.add_funds_button)
        withdrawFundsButton = view.findViewById(R.id.withdraw_funds_button)
        transactionHistoryRecyclerView = view.findViewById(R.id.transaction_history_recycler_view)

        transactionHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        transactionAdapter = TransactionAdapter(generateDummyTransactions())
        transactionHistoryRecyclerView.adapter = transactionAdapter

        setupWalletFunctionality()

        return view
    }

    private fun setupWalletFunctionality() {
        walletBalanceTextView.text = "$78.213" // Set the wallet balance here
        // Implement wallet functionality here
        // For example, handle add funds, withdraw funds, and display transaction history
    }

    private fun generateDummyTransactions(): List<Transaction> {
        return listOf(
            Transaction("2025-03-01", "Service Payment", "$50.00"),
            Transaction("2025-03-02", "Refund", "$15.00"),
            Transaction("2025-03-03", "Service Payment", "$20.00"),
            Transaction("2025-03-04", "Top-Up", "$100.00"),
            Transaction("2025-03-05", "Withdrawal", "$30.00"),
            Transaction("2025-03-06", "Service Payment", "$25.00"),
            Transaction("2025-03-07", "Top-Up", "$50.00"),
            Transaction("2025-03-08", "Service Payment", "$10.00"),
            Transaction("2025-03-09", "Withdrawal", "$40.00"),
            Transaction("2025-03-10", "Service Payment", "$35.00"),
            Transaction("2025-03-11", "Top-Up", "$75.00"),
            Transaction("2025-03-12", "Service Payment", "$60.00"),
            Transaction("2025-03-13", "Withdrawal", "$20.00"),
            Transaction("2025-03-14", "Service Payment", "$45.00"),
            Transaction("2025-03-15", "Top-Up", "$30.00"),
            Transaction("2025-03-16", "Service Payment", "$55.00"),
            Transaction("2025-03-17", "Refund", "$5.00"),
            Transaction("2025-03-18", "Service Payment", "$25.00"),
            Transaction("2025-03-19", "Top-Up", "$90.00"),
            Transaction("2025-03-20", "Service Payment", "$80.00")
        )
    }
}
