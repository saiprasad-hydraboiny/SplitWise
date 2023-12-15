package com.scaler.splitwise.strategies;

import com.scaler.splitwise.Services.Transaction;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.ExpenseUser;
import com.scaler.splitwise.models.ExpenseUserType;

import java.util.*;


class minHeapComparator implements Comparator<ExpenseUser>{
    public int compare(ExpenseUser eu1,ExpenseUser eu2){
        return Integer.compare(eu1.getAmount(), eu2.getAmount());
    }
}

class maxHeapComparator implements Comparator<ExpenseUser>{
    public int compare(ExpenseUser eu1,ExpenseUser eu2){
        return Integer.compare(eu2.getAmount(), eu1.getAmount());
    }
}



public class TwoHeapsStrategy implements SettleUpStrategy{
    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        PriorityQueue<ExpenseUser>minHeap=new PriorityQueue<>(new minHeapComparator());
        PriorityQueue<ExpenseUser>maxHeap=new PriorityQueue<>(new maxHeapComparator());

        for(Expense expense:expenses){
            for(ExpenseUser expenseUser:expense.getExpenseUsers()){
                if(expenseUser.getExpenseUserType().equals(ExpenseUserType.PAID)){
                    maxHeap.add(expenseUser);
                }
                else{
                    minHeap.add(expenseUser);
                }
            }
        }

        List<Transaction>transactions=new ArrayList<>();

        while(minHeap.isEmpty() && maxHeap.isEmpty()){
            Transaction transaction=new Transaction();
            int get= maxHeap.element().getAmount();
            int give= minHeap.element().getAmount();

            int settling_Amount=Math.min(Math.abs(get),Math.abs(give));
            transaction.setAmount(settling_Amount);
            transaction.setFrom(minHeap.element().getUser());
            transaction.setTo(maxHeap.element().getUser());
            transactions.add(transaction);

            if(settling_Amount==Math.abs(get) && settling_Amount==Math.abs(give)){
                maxHeap.remove(maxHeap.element());
                minHeap.remove(minHeap.element());
            }
            else if(settling_Amount==Math.abs(give) && settling_Amount<Math.abs(get)){
                minHeap.remove(minHeap.element());
                maxHeap.element().setAmount(maxHeap.element().getAmount()-settling_Amount);
            }
            else if(settling_Amount==Math.abs(get) && settling_Amount<Math.abs(give)){
                maxHeap.remove(maxHeap.element());
                minHeap.element().setAmount(minHeap.element().getAmount()+settling_Amount);
            }

        }

        return transactions;

    }
}
