package com.scaler.splitwise.strategies;

import com.scaler.splitwise.Services.Transaction;
import com.scaler.splitwise.models.Expense;

import java.util.List;
import java.util.TreeMap;

public interface SettleUpStrategy {

    public List<Transaction>settle(List<Expense>expenses);
}
